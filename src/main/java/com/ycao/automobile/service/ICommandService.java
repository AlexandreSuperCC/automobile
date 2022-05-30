package com.ycao.automobile.service;

import com.ycao.automobile.model.command.CommandDomain;

import java.util.List;

public interface ICommandService {

    void saveCommand(Integer uid,
                     String billing_country,
                     String billing_first_name,
                     String billing_last_name,
                     String billing_company,
                     String billing_address_1,
                     String billing_address_2,
                     String billing_city,
                     String billing_state,
                     String billing_postcode,
                     String billing_email,
                     String billing_phone,
                     String ship_to_different_address,
                     String shipping_country,
                     String shipping_first_name,
                     String shipping_last_name,
                     String shipping_company,
                     String shipping_address_1,
                     String shipping_address_2,
                     String shipping_city,
                     String shipping_state,
                     String shipping_postcode,
                     String order_comments);

    /**
     * get all the commands of one user, including the details of commands
     * @param uid the id of the user
     * @return the commands of the user
     */
    List<CommandDomain> getAllCommandFromUser(Integer uid);
}
