// Generated code from Butter Knife. Do not modify!
package com.suctan.huigang.fragment;

import android.widget.GridView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class FourthFragment$$ViewBinder<T extends FourthFragment> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends FourthFragment> implements Unbinder {
    protected T target;

    protected InnerUnbinder(T target, Finder finder, Object source) {
      this.target = target;

      target.I_have_received_Up_gridview = finder.findRequiredViewAsType(source, 2131558715, "field 'I_have_received_Up_gridview'", GridView.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.I_have_received_Up_gridview = null;

      this.target = null;
    }
  }
}
