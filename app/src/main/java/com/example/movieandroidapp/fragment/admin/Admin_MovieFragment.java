package com.example.movieandroidapp.fragment.admin;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.movieandroidapp.R;
import com.example.movieandroidapp.Utility.DataLocalManager;
import com.example.movieandroidapp.Utility.Extension;
import com.example.movieandroidapp.Utility.RealPathUtil;
import com.example.movieandroidapp.contract.movie.GetGenre;
import com.example.movieandroidapp.contract.movie.PostUpdateMovieContract;
import com.example.movieandroidapp.model.Genre;
import com.example.movieandroidapp.model.movie.Movie;
import com.example.movieandroidapp.model.movie.PostMovie;
import com.example.movieandroidapp.presenter.movie.GetGenrePresenter;
import com.example.movieandroidapp.presenter.movie.PostMoviePresenter;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Admin_MovieFragment extends Fragment {


    private static final String ARG_Movie = "param1";

    // TODO: Rename and change types of parameters
    private Movie movie;

    private static final int MY_REQUEST_CODE = 10;


    private View mView;

    //value of dropdown quality
    String[] itemsDropdownQuality = {"1080p60", "720p60", "480p", "360p"};
    AutoCompleteTextView autoCompleteTxt_quality;
    ArrayAdapter<String> adapterItemQuality;

    //value of dropdown genre
    List<String> genres;
    ArrayAdapter<String> adapterItemGenre;
    AutoCompleteTextView autoCompleteTxt_genre;


    //value of dropdown country
    String[] itemsDropdownCountry = {"US", "UK", "Viet Nam", "Singapore", "Korea", "Japan", "Thailand", "China"};
    AutoCompleteTextView autoCompleteTxt_country;
    ArrayAdapter<String> adapterItemCountry;

    //value of dropdown class name
    String[] itemsDropdownClassname = {"Basic", "Premium"};
    AutoCompleteTextView autoCompleteTxt_className;
    ArrayAdapter<String> adapterItemClassname;

    private PostMovie postMovie;
    private ImageView admin_movie_add_thumbnail;

    private DatePickerDialog datePickerDialog;
    private EditText admin_movie_add_releaseTime, admin_movie_add_age,
            admin_movie_add_running_time, admin_movie_upload_photos,
            admin_movie_upload_video, admin_movie_add_title, admin_movie_add_content;
    private String stateCurrent;
    private Button btn_add_movie;
    private TextView error_message_add_movie, title_fragment;

    ProgressDialog progress;

    private ActivityResultLauncher<Intent> launchSomeActivity
            = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    // do your operation from here....
                    if (data != null
                            && data.getData() != null) {
                        Uri uri = data.getData();
                        if (stateCurrent.equals("thumb")) {
                            setDataThumb(uri);
                        } else if (stateCurrent.equals("cover")) {
                            setCover(uri);
                        } else {
                            setVideo(uri);
                        }
                    }
                }
            });


    public Admin_MovieFragment() {
    }

    public static Admin_MovieFragment newInstance(Movie movieParam) {
        Admin_MovieFragment fragment = new Admin_MovieFragment();
        Bundle args = new Bundle();
        args.putString(ARG_Movie, Extension.GsonUtil().toJson(movieParam));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            movie = Extension.GsonUtil().fromJson(getArguments().getString(ARG_Movie), Movie.class);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_admin__movie, container, false);
        init();
        return mView;
    }

    private void init() {
        progress = new ProgressDialog(mView.getContext());
        progress.setTitle("Upload movie");
        progress.setMessage("Wait while uploading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog


        postMovie = new PostMovie();
        defaultValue();

        title_fragment = mView.findViewById(R.id.title_fragment);

        admin_movie_upload_photos = mView.findViewById(R.id.admin_movie_upload_photos);
        admin_movie_upload_video = mView.findViewById(R.id.admin_movie_upload_video);
        admin_movie_add_thumbnail = mView.findViewById(R.id.admin_movie_add_thumbnail);
        admin_movie_add_releaseTime = mView.findViewById(R.id.admin_movie_add_releaseTime);
        admin_movie_add_title = mView.findViewById(R.id.admin_movie_add_title);
        admin_movie_add_content = mView.findViewById(R.id.admin_movie_add_content);
        admin_movie_add_running_time = mView.findViewById(R.id.admin_movie_add_running_time);
        admin_movie_add_age = mView.findViewById(R.id.admin_movie_add_age);
        error_message_add_movie = mView.findViewById(R.id.error_message_add_movie);

        autoCompleteTxt_quality = mView.findViewById(R.id.auto_complete_quality);
        autoCompleteTxt_genre = mView.findViewById(R.id.auto_complete_genre);
        autoCompleteTxt_country = mView.findViewById(R.id.auto_complete_country);
        autoCompleteTxt_className = mView.findViewById(R.id.auto_complete_className);


        initDatePicker();
        getReleaseDate();
        getThumbnail();
        getVideo();
        getCoverImage();

        getGenre();
        renderDropdownQuality();
        renderDropdownGenre();
        renderDropdownCountry();
        renderDropdownClassName();
        submitForm();
        setValueFormForUpdate();
    }

    private void setValueFormForUpdate() {
        if (movie != null) {
            title_fragment.setText("Update movie");
            if (!movie.getThumbnail().isEmpty()) {
                Picasso.get().load(movie.getCoverImage()).into(admin_movie_add_thumbnail);
            }

            admin_movie_add_title.setText(movie.getMovieName());
            admin_movie_add_content.setText(movie.getDescription());
            admin_movie_add_releaseTime.setText(Extension.formatDate(movie.getReleaseTime()));
            admin_movie_add_running_time.setText(movie.getRunningTime().toString());
            admin_movie_add_age.setText(movie.getAge());
            admin_movie_upload_photos.setText(movie.getCoverImage());
            admin_movie_upload_video.setText(movie.getMovieURL());
        }
    }

    private void submitForm() {
        btn_add_movie = mView.findViewById(R.id.btn_add_movie);
        //submit to set value for object postMovie
        btn_add_movie.setOnClickListener(t -> {
            progress.show();
            if (!admin_movie_add_running_time.getText().toString().isEmpty()) {
                postMovie.setRunningTime(Float.parseFloat(admin_movie_add_running_time.getText().toString()));
            }
            postMovie.setAge(admin_movie_add_age.getText().toString());
            postMovie.setUserID(DataLocalManager.getUserId());
            postMovie.setMovieName(admin_movie_add_title.getText().toString());
            postMovie.setDescription(admin_movie_add_content.getText().toString());
            //check is update
            if (movie != null) {
                updateMovieToServer(postMovie);
            } else {
                postMovieToServer(postMovie);
            }
        });
    }

    private void postMovieToServer(PostMovie movie) {
        PostUpdateMovieContract.View view = new PostUpdateMovieContract.View() {
            @Override
            public void onResponseSuccess() {
                error_message_add_movie.setVisibility(View.GONE);
                progress.dismiss();
                Toast.makeText(mView.getContext(), "Create movie successfully!", Toast.LENGTH_SHORT).show();
                clearDataForm();
            }

            @Override
            public void onResponseFailure(String message) {
                progress.dismiss();
                error_message_add_movie.setText(message);
                error_message_add_movie.setVisibility(View.VISIBLE);
                Toast.makeText(mView.getContext(), message, Toast.LENGTH_SHORT).show();
            }
        };
        PostMoviePresenter postMoviePresenter = new PostMoviePresenter(view);
        postMoviePresenter.requestMovie(movie,"post");
    }
    private void updateMovieToServer(PostMovie postMovie) {
        postMovie.setLanguage(movie.getLanguage());
        postMovie.setPublicationTime(java.time.LocalDate.now().toString());
        postMovie.setMovieID(movie.getMovieID());
        postMovie.setReleaseTime(admin_movie_add_releaseTime.getText().toString());
        PostUpdateMovieContract.View view = new PostUpdateMovieContract.View() {
            @Override
            public void onResponseSuccess() {
                error_message_add_movie.setVisibility(View.GONE);
                progress.dismiss();
                Toast.makeText(mView.getContext(), "Update movie successfully!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponseFailure(String message) {
                progress.dismiss();
                error_message_add_movie.setText(message);
                error_message_add_movie.setVisibility(View.VISIBLE);
                Toast.makeText(mView.getContext(), message, Toast.LENGTH_SHORT).show();
            }
        };
        PostMoviePresenter postMoviePresenter = new PostMoviePresenter(view);
        postMoviePresenter.requestMovie(postMovie,"update");
    }

    private void renderDropdownQuality() {
        if (movie != null) {
            autoCompleteTxt_quality.setText(movie.getQuality());
            postMovie.setQuality(movie.getQuality());
        }

        adapterItemQuality = new ArrayAdapter<>(mView.getContext(), R.layout.dropdown_normal_item, itemsDropdownQuality);

        autoCompleteTxt_quality.setAdapter(adapterItemQuality);

        autoCompleteTxt_quality.setOnItemClickListener((parent, view, position, id) -> {
            String item = parent.getItemAtPosition(position).toString();
            postMovie.setQuality(item);
        });
    }

    private void getGenre() {
        genres = new ArrayList<>();
        GetGenre.View view = new GetGenre.View() {
            @Override
            public void onResponseSuccess(List<Genre> genreList) {
                for (Genre genre : genreList) {
                    genres.add(genre.getGenreName());
                }
            }

            @Override
            public void onResponseFailure(String message) {

            }
        };
        GetGenrePresenter getGenrePresenter = new GetGenrePresenter(view);
        getGenrePresenter.requestGetGenres();
    }

    private void renderDropdownGenre() {
        List<String> list = new ArrayList<>();

        if (movie != null) {
            autoCompleteTxt_genre.setText(movie.getGenres().get(0));
            list.add(movie.getGenres().get(0));
            postMovie.setGenreNames(list);
        }

        adapterItemGenre = new ArrayAdapter<>(mView.getContext(), R.layout.dropdown_normal_item, genres);

        autoCompleteTxt_genre.setAdapter(adapterItemGenre);
        //set movie when action is updating
        autoCompleteTxt_genre.setOnItemClickListener((parent, view, position, id) -> {
            String item = parent.getItemAtPosition(position).toString();
            list.clear();
            list.add(item);
            postMovie.setGenreNames(list);
        });
    }

    private void renderDropdownCountry() {
        if (movie != null) {
            autoCompleteTxt_country.setText(movie.getCountry());
            postMovie.setCountry(movie.getCountry());
        }
        adapterItemCountry = new ArrayAdapter<>(mView.getContext(), R.layout.dropdown_normal_item, itemsDropdownCountry);

        autoCompleteTxt_country.setAdapter(adapterItemCountry);

        autoCompleteTxt_country.setOnItemClickListener((parent, view, position, id) -> {
            String item = parent.getItemAtPosition(position).toString();
            postMovie.setCountry(item);
            postMovie.setLanguage(item);
        });
    }

    private void renderDropdownClassName() {
        //for update
        if (movie != null) {
            autoCompleteTxt_className.setText(movie.getClassName());
            postMovie.setClassName(movie.getClassName());
        }

        adapterItemClassname = new ArrayAdapter<>(mView.getContext(), R.layout.dropdown_normal_item, itemsDropdownClassname);

        autoCompleteTxt_className.setAdapter(adapterItemClassname);

        autoCompleteTxt_className.setOnItemClickListener((parent, view, position, id) -> {
            String item = parent.getItemAtPosition(position).toString();
            postMovie.setClassName(item);
        });
    }

    private void getReleaseDate() {
        admin_movie_add_releaseTime.setOnClickListener(t -> {
            datePickerDialog.show();
        });
    }

    private void getThumbnail() {
        admin_movie_add_thumbnail.setOnClickListener(t -> {
            stateCurrent = "thumb";
            onClickRequestPermission();
        });
    }

    private void setDataThumb(Uri uri) {
        String strRealPath = RealPathUtil.getRealPath(mView.getContext(), uri);
        File file = new File(strRealPath);
        if (Extension.acceptImage(file)) {
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(mView.getContext().getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            admin_movie_add_thumbnail.setImageBitmap(bitmap);

            postMovie.setThumbnail(file);
        } else {
            Toast.makeText(mView.getContext(), "Please choose image have format [jpg,png,gif,jpeg] ", Toast.LENGTH_SHORT).show();
        }

    }

    private void getVideo() {
        admin_movie_upload_video.setOnClickListener(t -> {
            stateCurrent = "video";

            onClickRequestPermission();

        });
    }

    private void setVideo(Uri uri) {
        String strRealPath = RealPathUtil.getRealPath(mView.getContext(), uri);
        File file = new File(strRealPath);
        if (!Extension.acceptImage(file)) {
            admin_movie_upload_video.setText(strRealPath);
            postMovie.setMovie(file);
        } else {
            Toast.makeText(mView.getContext(), "Please choose video again", Toast.LENGTH_SHORT).show();
        }
    }

    private void getCoverImage() {
        admin_movie_upload_photos.setOnClickListener(t -> {
            stateCurrent = "cover";

            onClickRequestPermission();

        });
    }

    private void setCover(Uri uri) {
        String strRealPath = RealPathUtil.getRealPath(mView.getContext(), uri);
        File file = new File(strRealPath);
        if (Extension.acceptImage(file)) {
            admin_movie_upload_photos.setText(strRealPath);
            postMovie.setCoverImage(file);
        } else {
            Toast.makeText(mView.getContext(), "Please choose image have format [jpg,png,gif,jpeg] ", Toast.LENGTH_SHORT).show();
        }
    }

    private void onClickRequestPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            openGallery();
            return;
        }
        if (mView.getContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openGallery();
        } else {
            String[] permision = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permision, MY_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            }
        }
    }

    private void openGallery() {
        Intent i = new Intent();
        i.setType("image/* video/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        launchSomeActivity.launch(i);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            String date = makeDateString(day, month, year);
            postMovie.setReleaseTime(date);
            admin_movie_add_releaseTime.setText(date);
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(mView.getContext(), style, dateSetListener, year, month, day);
    }

    private String makeDateString(int day, int month, int year) {
        return year + "-" + month + "-" + day;
    }

    private void clearDataForm() {
        admin_movie_add_thumbnail.setImageResource(R.drawable.not_available);
        admin_movie_add_title.setText("");
        admin_movie_add_content.setText("");
        admin_movie_add_releaseTime.setText("");
        admin_movie_add_running_time.setText("");
        admin_movie_add_age.setText("");
        admin_movie_upload_photos.setText("");
        admin_movie_upload_video.setText("");
        autoCompleteTxt_quality.setText("");
        autoCompleteTxt_genre.setText("");
        autoCompleteTxt_className.setText("");
        autoCompleteTxt_country.setText("");

        defaultValue();
    }
    private void defaultValue(){
        postMovie.setActor("Group1");
        postMovie.setDirector("Group1");
        postMovie.setMovieTypeName("Short Video");
        postMovie.setSubtitle("This is subtitle");
        postMovie.setPublicationTime(java.time.LocalDate.now().toString());
        postMovie.setMovieID(DataLocalManager.getUserId());
    }
}