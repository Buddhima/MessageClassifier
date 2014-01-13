package models.com.mc.workers;

import akka.japi.Procedure;

public abstract class Behavior implements Procedure<Object> {
  public abstract void apply(Object message);
}
