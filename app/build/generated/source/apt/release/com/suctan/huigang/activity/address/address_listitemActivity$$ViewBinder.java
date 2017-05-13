// Generated code from Butter Knife. Do not modify!
package com.suctan.huigang.activity.address;

import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class address_listitemActivity$$ViewBinder<T extends address_listitemActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends address_listitemActivity> implements Unbinder {
    protected T target;

    protected InnerUnbinder(T target, Finder finder, Object source) {
      this.target = target;

      target.people = finder.findRequiredViewAsType(source, 2131558610, "field 'people'", EditText.class);
      target.phone = finder.findRequiredViewAsType(source, 2131558611, "field 'phone'", EditText.class);
      target.Area = finder.findRequiredViewAsType(source, 2131558612, "field 'Area'", EditText.class);
      target.Community = finder.findRequiredViewAsType(source, 2131558613, "field 'Community'", EditText.class);
      target.AllAera = finder.findRequiredViewAsType(source, 2131558614, "field 'AllAera'", EditText.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.people = null;
      target.phone = null;
      target.Area = null;
      target.Community = null;
      target.AllAera = null;

      this.target = null;
    }
  }
}
