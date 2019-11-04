/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerInterfaces;

import Models.Request;
import Models.Response;

/**
 *
 * @author madsh
 */
public interface IRequestDelegater {
     public Response delegate(Request request);
}
