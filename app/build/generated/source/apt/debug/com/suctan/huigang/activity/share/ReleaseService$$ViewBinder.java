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

public class ReleaseService$$ViewBinder<T extends ReleaseService> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends ReleaseService> implements Unbinder {
    protected T target;

    protected InnerUnbinder(T target, Finder finder, Object source) {
      this.target = target;

      target.loginBack = finder.findRequiredViewAsType(source, 2131558784, "field 'loginBack'", ImageView.class);
      target.loginTitle = finder.findRequiredViewAsType(source, 2131558785, "field 'loginTitle'", TextView.class);
      target.search = finder.findRequiredViewAsType(source, 2131558786, "field 'search'", ImageView.class);
      target.addTime = finder.findRequiredViewAsType(source, 2131559155, "field 'addTime'", Button.class);
      target.minusTime = finder.findRequiredViewAsType(source, 2131559153, "field 'minusTime'", Button.class);
      target.service_time = finder.findRequiredViewAsType(source, 2131559154, "field 'service_time'", TextView.class);
      target.service_money = finder.findRequiredViewAsType(source, 2131559143, "field 'service_money'", TextView.class);
      target.service_title = finder.findRequiredViewAsType(source, 2131558661, "field 'service_title'", TextView.class);
      target.service_content = finder.findRequiredViewAsType(source, 2131559152, "field 'service_content'", TextView.class);
      target.eat_select_time = finder.findRequiredViewAsType(source, 2131559156, "field 'eat_select_time'", Button.class);
      target.service_fb = finder.findRequiredViewAsType(source, 2131559157, "field 'service_fb'", Button.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.loginBack = null;
      target.loginTitle = null;
      target.search = null;
      target.addTime = null;
      target.minusTime = null;
      target.service_time = null;
      target.service_money = null;
      target.service_title = null;
      target.service_content = null;
      target.eat_select_time = null;
      target.service_fb = null;

      this.target = null;
    }
  }
}
