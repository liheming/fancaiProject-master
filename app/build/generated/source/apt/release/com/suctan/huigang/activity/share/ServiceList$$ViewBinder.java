// Generated code from Butter Knife. Do not modify!
package com.suctan.huigang.activity.share;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class ServiceList$$ViewBinder<T extends ServiceList> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends ServiceList> implements Unbinder {
    protected T target;

    protected InnerUnbinder(T target, Finder finder, Object source) {
      this.target = target;

      target.loginBack = finder.findRequiredViewAsType(source, 2131558798, "field 'loginBack'", ImageView.class);
      target.loginTitle = finder.findRequiredViewAsType(source, 2131558799, "field 'loginTitle'", TextView.class);
      target.search = finder.findRequiredViewAsType(source, 2131558800, "field 'search'", ImageView.class);
      target.serviceFb = finder.findRequiredViewAsType(source, 2131558801, "field 'serviceFb'", Button.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.loginBack = null;
      target.loginTitle = null;
      target.search = null;
      target.serviceFb = null;

      this.target = null;
    }
  }
}
