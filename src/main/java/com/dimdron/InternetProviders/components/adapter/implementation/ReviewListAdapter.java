package com.dimdron.InternetProviders.components.adapter.implementation;

import com.dimdron.InternetProviders.components.adapter.Adapter;
import com.dimdron.InternetProviders.components.adapter.cestomViewCells.ReviewCustomCell;
import com.dimdron.InternetProviders.dbconnection.model.Review;
import javafx.scene.layout.Pane;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 26.05.14
 * Time: 18:51
 * To change this template use File | Settings | File Templates.
 */
public class ReviewListAdapter implements Adapter<Review> {
    private Collection<Review>  collection;
    public ReviewListAdapter(Collection<Review> reviews) {
        collection = reviews;
    }
    @Override
    public Pane generateView(Object object) {
        Review review = (Review) object;
        ReviewCustomCell cell = new ReviewCustomCell();
        cell.setSupport(review.getSupport());
        cell.setSpeed(review.getRate());
        cell.setConnection(review.getCommunication());
        cell.setReviewText(review.getComment());
        cell.setReviewId(review.getId());
        return cell;

    }

    @Override
    public Collection<Review> getCollection() {
        return collection;
    }
}
