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

public class ServiceDetail$$ViewBinder<T extends ServiceDetail> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends ServiceDetail> implements Unbinder {
    protected T target;

    protected InnerUnbinder(T target, Finder finder, Object source) {
      this.target = target;

      target.loginBack = finder.findRequiredViewAsType(source, 2131558798, "field 'loginBack'", ImageView.class);
      target.loginTitle = finder.findRequiredViewAsType(source, 2131558799, "field 'loginTitle'", TextView.class);
      target.search = finder.findRequiredViewAsType(source, 2131558800, "field 'search'", ImageView.class);
      target.user_name = finder.findRequiredViewAsType(source, 2131559152, "field 'user_name'", TextView.class);
      target.user_phone = finder.findRequiredViewAsType(source, 2131559153, "field 'user_phone'", TextView.class);
      target.user_icon = finder.findRequiredViewAsType(source, 2131559147, "field 'user_icon'", ImageView.class);
      target.address = finder.findRequiredViewAsType(source, 2131559154, "field 'address'", TextView.class);
      target.content = finder.findRequiredViewAsType(source, 2131559150, "field 'content'", TextView.class);
      target.price = finder.findRequiredViewAsType(source, 2131559151, "field 'price'", TextView.class);
      target.pub_time = finder.findRequiredViewAsType(source, 2131559149, "field 'pub_time'", TextView.class);
      target.pub_user = finder.findRequiredViewAsType(source, 2131559148, "field 'pub_user'", TextView.class);
      target.help = finder.findRequiredViewAsType(source, 2131559155, "field 'help'", Button.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.loginBack = null;
      target.loginTitle = null;
      target.search = null;
      target.user_name = null;
      target.user_phone = null;
      target.user_icon = null;
      target.address = null;
      target.content = null;
      target.price = null;
      target.pub_time = null;
      target.pub_user = null;
      target.help = null;

      this.target = null;
    }
  }
}
