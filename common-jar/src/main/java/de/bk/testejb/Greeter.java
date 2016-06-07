package de.bk.testejb;

import java.io.Serializable;

public interface Greeter extends Serializable {
    
    String greet(String user);
}
