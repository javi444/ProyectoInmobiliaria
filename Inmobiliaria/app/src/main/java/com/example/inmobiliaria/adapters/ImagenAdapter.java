package com.example.inmobiliaria.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.inmobiliaria.R;

import java.util.Arrays;
import java.util.List;

public class ImagenAdapter extends PagerAdapter {

    private Context cxt;
    private List<String> urls;
    private ImageView[] photos;
    private LayoutInflater layoutInflater;

    public ImagenAdapter() {
    }

    @Override
    public int getCount() {
        return urls.size();
    }

    public ImagenAdapter(Context cxt, List<String> urls, ImageView[] photos, LayoutInflater layoutInflater) {
        this.cxt = cxt;
        this.urls = urls;
        this.photos = photos;
        this.layoutInflater = layoutInflater;
    }

    public ImagenAdapter(Context cxt, List<String> urls) {
        this.cxt = cxt;
        this.urls = urls;
    }

    public Context getCxt() {
        return cxt;
    }

    public void setCxt(Context cxt) {
        this.cxt = cxt;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public ImageView[] getPhotos() {
        return photos;
    }

    public void setPhotos(ImageView[] photos) {
        this.photos = photos;
    }

    public LayoutInflater getLayoutInflater() {
        return layoutInflater;
    }

    public void setLayoutInflater(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImagenAdapter that = (ImagenAdapter) o;

        if (cxt != null ? !cxt.equals(that.cxt) : that.cxt != null) return false;
        if (urls != null ? !urls.equals(that.urls) : that.urls != null) return false;

        if (!Arrays.equals(photos, that.photos)) return false;
        return layoutInflater != null ? layoutInflater.equals(that.layoutInflater) : that.layoutInflater == null;
    }

    @Override
    public int hashCode() {
        int result = cxt != null ? cxt.hashCode() : 0;
        result = 31 * result + (urls != null ? urls.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(photos);
        result = 31 * result + (layoutInflater != null ? layoutInflater.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ImagenAdapter{" +
                "cxt=" + cxt +
                ", urls=" + urls +
                ", photos=" + Arrays.toString(photos) +
                ", layoutInflater=" + layoutInflater +
                '}';
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) cxt.getSystemService(cxt.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.imagenlayout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        Glide
                .with(this.cxt)
                .load(urls.get(position))
                .into(imageView);


        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
}
