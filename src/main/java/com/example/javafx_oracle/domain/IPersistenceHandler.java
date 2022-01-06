package com.example.javafx_oracle.domain;

import java.util.List;

public interface IPersistenceHandler {

    public List<Friend> getFriends();
    public List<Friend> sortFriendsByID();
    public List<Friend> sortFriendsByName();
    public boolean createFriend(Friend friend);
    public boolean updateFriend(Friend friend, int id);
    public boolean deleteFriend(int id);
    public boolean deleteAll();

}