package com.example.javafx_oracle.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import com.example.javafx_oracle.domain.Friend;
import com.example.javafx_oracle.domain.IPersistenceHandler;
import com.example.javafx_oracle.persistence.PersistenceHandler;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {

    IPersistenceHandler persistenceHandler = PersistenceHandler.getInstance();

    @FXML
    private TextField name;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField nameUpdate;

    @FXML
    private TextField phoneNumberUpdate;

    @FXML
    private TextField idUpdate;

    @FXML
    private TextField idDelete;

    @FXML
    private ListView<Friend> friendsListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        friendsListView.getItems().addAll(persistenceHandler.getFriends());
    }

    @FXML
    void addFriend(ActionEvent event) {
        Friend friend = new Friend(null, name.getText(), Integer.parseInt(phoneNumber.getText()));
        if(persistenceHandler.createFriend(friend)){
            System.out.println("Friend inserted into database");
        } else {
            System.out.println("Something went wrong");
        }
        updateUI();
    }

    @FXML
    void updateFriend(ActionEvent event) {
        Friend friend = new Friend(null, nameUpdate.getText(), Integer.parseInt(phoneNumberUpdate.getText()));
        if(persistenceHandler.updateFriend(friend, Integer.parseInt(idUpdate.getText()))){
            System.out.println("Friend updated in database");
        } else {
            System.out.println("Something went wrong");
        }
        updateUI();
    }

    @FXML
    void deleteFriend(ActionEvent event) {
        if(persistenceHandler.deleteFriend(Integer.parseInt(idDelete.getText()))){
            System.out.println("Friend deleted from database");
        } else {
            System.out.println("Something went wrong");
        }
        updateUI();
    }

    @FXML
    void deleteAll(ActionEvent event) {
        if(persistenceHandler.deleteAll()){
            System.out.println("All friends are deleted from database");
        } else {
            System.out.println("Something went wrong");
        }
        updateUI();
    }

    @FXML
    void sortID(ActionEvent event) {
        name.clear();
        phoneNumber.clear();
        friendsListView.getItems().clear();
        friendsListView.getItems().addAll(persistenceHandler.sortFriendsByID());
    }
    @FXML
    void sortName(ActionEvent event) {
        name.clear();
        phoneNumber.clear();
        friendsListView.getItems().clear();
        friendsListView.getItems().addAll(persistenceHandler.sortFriendsByName());
    }

    private void updateUI(){
        name.clear();
        phoneNumber.clear();
        friendsListView.getItems().clear();
        friendsListView.getItems().addAll(persistenceHandler.getFriends());
    }

}