// Generated code from Butter Knife. Do not modify!
package com.suctan.huigang.activity.myself;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class WithdarwasActivity$$ViewBinder<T extends WithdarwasActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends WithdarwasActivity> implements Unbinder {
    protected T target;

    protected InnerUnbinder(T target, Finder finder, Object source) {
      this.target = target;

      target.btnwitharwals = finder.findRequiredViewAsType(source, 2131559240, "field 'btnwitharwals'", Button.class);
      target.witharwals = finder.findRequiredViewAsType(source, 2131559237, "field 'witharwals'", EditText.class);
      target.all_money = finder.findRequiredViewAsType(source, 2131559238, "field 'all_money'", TextView.class);
      target.select_AllMoney = finder.findRequiredViewAsType(source, 2131559239, "field 'select_AllMoney'", TextView.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.btnwitharwals = null;
      target.witharwals = null;
      target.all_money = null;
      target.select_AllMoney = null;

      this.target = null;
    }
  }
}
