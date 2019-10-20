/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication.Enums;

import java.io.Serializable;

/**
 *
 * @author madsh
 */
public enum RequestType implements Serializable {
    LOGIN,
    LOGOUT,
    CREATE_NEW_USER,
    SEARCH,
    GET_PROFILE,
    UPDATE_PROFILE,
    UPDATE_PASSWORD,
    UPDATE_USERNAME,
    DELETE_ACCOUNT,
    FOLLOW_PROFILE,
    FOLLOW_TRAINING_PROGRAM,
    SEND_BUDDY_REQUEST,
    ACCEPT_BUDDY_REQUEST,
    SET_GOAL,
    SET_STAT,
    REMOVE_STAT,
    SEND_MESSAGE
}
