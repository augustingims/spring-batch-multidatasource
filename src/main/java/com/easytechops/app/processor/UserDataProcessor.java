package com.easytechops.app.processor;

import com.easytechops.app.model.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by TECHAP KEMADJEU AUGUSTIN <augustingims@gmail.com> on 12/1/24
 *
 * @author : TECHAP KEMADJEU AUGUSTIN <augustingims@gmail.com>
 * @date : 12/1/24
 */
@Component
public class UserDataProcessor implements ItemProcessor<User, User> {
  @Override
  public User process(User item) {
    return item;
  }
}
