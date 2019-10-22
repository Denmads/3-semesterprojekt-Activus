/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.Actions;

import Persistence.IDatabaseAction;

/**
 *
 * @author madsh
 */
public abstract class IDatabaseCreateAction <T>extends IDatabaseAction<T> {
    public abstract int getInsertedId ();
}
