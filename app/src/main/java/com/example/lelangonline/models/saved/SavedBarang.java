package com.example.lelangonline.models.saved;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.lelangonline.models.converters.SourceConverter;
import com.example.lelangonline.models.saved.SavedBarang;
import com.example.lelangonline.utils.Constants;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = Constants.SAVED_TABLE_NAME)
@TypeConverters(SourceConverter.class)
public class SavedBarang implements Parcelable {
    public SavedBarang(){

    }
    @PrimaryKey(autoGenerate = true)
    private int uid;
    @SerializedName("updated_at")
    @ColumnInfo(name = "updated_at")
    private String updatedAt;

    @SerializedName("photo")
    @ColumnInfo(name = "photo")
    private String photo;

    @SerializedName("initial_price")
    @ColumnInfo(name = "initial_price")
    private int initialPrice;

    @SerializedName("created_at")
    @ColumnInfo(name = "created_at")
    private String createdAt;

    @SerializedName("item_name")
    @ColumnInfo(name = "item_name")
    private String itemName;

    @SerializedName("id")
    @ColumnInfo(name = "id")
    private int id;

    @SerializedName("deskripsi")
    @ColumnInfo(name = "deskripsi")
    private String deskripsi;

    @SerializedName("category")
    @ColumnInfo(name = "category")
    private String category;

    @SerializedName("status")
    @ColumnInfo(name = "status")
    private String status;

    protected SavedBarang(Parcel in) {
        updatedAt = in.readString();
        photo = in.readString();
        initialPrice = in.readInt();
        createdAt = in.readString();
        itemName = in.readString();
        id = in.readInt();
        deskripsi = in.readString();
        category = in.readString();
        status = in.readString();
    }

    @Ignore
    public SavedBarang(int id, String category, String deskripsi, String itemName, String photo, int initialPrice, String status, String createdAt, String updatedAt) {
        this.id = id;
        this.category = category;
        this.deskripsi = deskripsi;
        this.itemName = itemName;
        this.photo = photo;
        this.initialPrice = initialPrice;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void setUpdatedAt(String updatedAt){
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt(){
        return updatedAt;
    }

    public void setPhoto(String photo){
        this.photo = photo;
    }

    public String getPhoto(){
        return photo;
    }

    public void setInitialPrice(int initialPrice){
        this.initialPrice = initialPrice;
    }

    public int getInitialPrice(){
        return initialPrice;
    }

    public void setCreatedAt(String createdAt){
        this.createdAt = createdAt;
    }

    public String getCreatedAt(){
        return createdAt;
    }

    public void setItemName(String itemName){
        this.itemName = itemName;
    }

    public String getItemName(){
        return itemName;
    }

    public void setId(int id){
        this.id = id;
    } public void setUid(int uid){
        this.uid = uid;
    }

    public int getId(){
        return id;
    }
    public int getUid(){
        return uid;
    }

    public void setDeskripsi(String deskripsi){
        this.deskripsi = deskripsi;
    }

    public String getDeskripsi(){
        return deskripsi;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public String getCategory(){
        return category;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    @Override
    public String toString(){
        return
                "SavedBarang{" +
                        "updated_at = '" + updatedAt + '\'' +
                        ",photo = '" + photo + '\'' +
                        ",initial_price = '" + initialPrice + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",item_name = '" + itemName + '\'' +
                        ",id = '" + id + '\'' +
                        ",deskripsi = '" + deskripsi + '\'' +
                        ",category = '" + category + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }

    public static final Parcelable.Creator<SavedBarang> CREATOR = new Parcelable.Creator<SavedBarang>() {
        @Override
        public SavedBarang createFromParcel(Parcel in) {
            return new SavedBarang(in);
        }

        @Override
        public SavedBarang[] newArray(int size) {
            return new SavedBarang[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(updatedAt);
        dest.writeString(photo);
        dest.writeInt(initialPrice);
        dest.writeString(createdAt);
        dest.writeString(itemName);
        dest.writeInt(id);
        dest.writeString(deskripsi);
        dest.writeString(category);
        dest.writeString(status);
    }
}
