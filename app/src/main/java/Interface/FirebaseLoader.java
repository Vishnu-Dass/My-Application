package Interface;



import java.util.List;

import Model.Restaurant;

public interface FirebaseLoader {
    void onFireStoreLoadSuccess(List<Restaurant> restaurants);
    void onFirestoreLoadFailed(String message);
}