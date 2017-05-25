// Generated code from Butter Knife. Do not modify!
package com.suctan.huigang.fragment;

import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class FragmentMySelft$$ViewBinder<T extends FragmentMySelft> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends FragmentMySelft> implements Unbinder {
    protected T target;

    protected InnerUnbinder(T target, Finder finder, Object source) {
      this.target = target;

      target.setting = finder.findRequiredViewAsType(source, 2131558931, "field 'setting'", ImageView.class);
      target.address = finder.findRequiredViewAsType(source, 2131558932, "field 'address'", LinearLayout.class);
      target.i_buy = finder.findRequiredViewAsType(source, 2131558933, "field 'i_buy'", LinearLayout.class);
      target.i_sell = finder.findRequiredViewAsType(source, 2131558934, "field 'i_sell'", LinearLayout.class);
      target.my_money = finder.findRequiredViewAsType(source, 2131558936, "field 'my_money'", LinearLayout.class);
      target.my_discount = finder.findRequiredViewAsType(source, 2131558937, "field 'my_discount'", LinearLayout.class);
      target.mykitchen = finder.findRequiredViewAsType(source, 2131558938, "field 'mykitchen'", LinearLayout.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.setting = null;
      target.address = null;
      target.i_buy = null;
      target.i_sell = null;
      target.my_money = null;
      target.my_discount = null;
      target.mykitchen = null;

      this.target = null;
    }
  }
}
