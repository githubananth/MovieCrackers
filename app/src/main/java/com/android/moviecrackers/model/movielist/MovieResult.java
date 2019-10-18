package com.android.moviecrackers.model.movielist;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.android.moviecrackers.R;
import com.android.moviecrackers.utility.CustomImageView;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "movie_table")
public class MovieResult implements Parcelable {

    public MovieResult() {
    }

    @PrimaryKey(autoGenerate = true)
    private int autoId;

    @SerializedName("popularity")
    @Expose
    public Double popularity;

    @SerializedName("vote_count")
    @Expose
    public Integer voteCount;

    @SerializedName("poster_path")
    @Expose
    public String posterPath;

    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("backdrop_path")
    @Expose
    public String backdropPath;

    @SerializedName("original_title")
    @Expose
    public String originalTitle;

    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("vote_average")
    @Expose
    public Double voteAverage;

    @SerializedName("overview")
    @Expose
    public String overview;

    @SerializedName("release_date")
    @Expose
    public String releaseDate;

    public int getAutoId() {
        return autoId;
    }

    public void setAutoId(int autoId) {
        this.autoId = autoId;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @BindingAdapter("android:thumbnail")
    public static void loadImage(ImageView imageView, String url) {
        CustomImageView.ImageViewURL(url, imageView, R.drawable.movie_thubnail_image);
    }


    protected MovieResult(Parcel in) {
        if (in.readByte() == 0) {
            popularity = null;
        } else {
            popularity = in.readDouble();
        }
        if (in.readByte() == 0) {
            voteCount = null;
        } else {
            voteCount = in.readInt();
        }
        posterPath = in.readString();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        backdropPath = in.readString();
        originalTitle = in.readString();
        title = in.readString();
        if (in.readByte() == 0) {
            voteAverage = null;
        } else {
            voteAverage = in.readDouble();
        }
        overview = in.readString();
        releaseDate = in.readString();
    }

    public static final Creator<MovieResult> CREATOR = new Creator<MovieResult>() {
        @Override
        public MovieResult createFromParcel(Parcel in) {
            return new MovieResult(in);
        }

        @Override
        public MovieResult[] newArray(int size) {
            return new MovieResult[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (popularity == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(popularity);
        }
        if (voteCount == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(voteCount);
        }
        parcel.writeString(posterPath);
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(backdropPath);
        parcel.writeString(originalTitle);
        parcel.writeString(title);
        if (voteAverage == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(voteAverage);
        }
        parcel.writeString(overview);
        parcel.writeString(releaseDate);
    }
}