// Generated code from Butter Knife. Do not modify!
package com.suctan.huigang.fragment;

import android.widget.GridView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class ThirdFragment$$ViewBinder<T extends ThirdFragment> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends ThirdFragment> implements Unbinder {
    protected T target;

    protected InnerUnbinder(T target, Finder finder, Object source) {
      this.target = target;

      target.My_evaluation_thridFrament_gridview = finder.findRequiredViewAsType(source, 2131559187, "field 'My_evaluation_thridFrament_gridview'", GridView.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.My_evaluation_thridFrament_gridview = null;

      this.target = null;
    }
  }
}
