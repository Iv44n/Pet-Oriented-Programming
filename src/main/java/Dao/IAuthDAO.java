package Dao;

import Models.User.PersistentUser;
import Models.User.UserForCreation;

public interface IAuthDAO {

  PersistentUser signUp(UserForCreation userForCreation);

  PersistentUser signInByEmail(String email, String password);

  PersistentUser signInAsAdmin(String email, String password);

  boolean signOut();
  
  PersistentUser getUser();
}
