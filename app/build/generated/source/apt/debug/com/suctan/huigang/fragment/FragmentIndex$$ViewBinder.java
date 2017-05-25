// Generated code from Butter Knife. Do not modify!
package com.suctan.huigang.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.GridView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import cn.bingoogolapple.badgeview.BGABadgeRadioButton;
import com.jude.rollviewpager.RollPagerView;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class FragmentIndex$$ViewBinder<T extends FragmentIndex> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends FragmentIndex> implements Unbinder {
    protected T target;

    protected InnerUnbinder(T target, Finder finder, Object source) {
      this.target = target;

      target.tab_index = finder.findRequiredViewAsType(source, 2131558726, "field 'tab_index'", BGABadgeRadioButton.class);
      target.tab_want = finder.findRequiredViewAsType(source, 2131558727, "field 'tab_want'", BGABadgeRadioButton.class);
      target.tab_do = finder.findRequiredViewAsType(source, 2131558728, "field 'tab_do'", BGABadgeRadioButton.class);
      target.rollPagerView = finder.findRequiredViewAsType(source, 2131558724, "field 'rollPagerView'", RollPagerView.class);
      target.more_text = finder.findRequiredViewAsType(source, 2131558730, "field 'more_text'", TextView.class);
      target.swipeRayout = finder.findRequiredViewAsType(source, 2131558681, "field 'swipeRayout'", SwipeRefreshLayout.class);
      target.gridView = finder.findRequiredViewAsType(source, 2131558731, "field 'gridView'", GridView.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.tab_index = null;
      target.tab_want = null;
      target.tab_do = null;
      target.rollPagerView = null;
      target.more_text = null;
      target.swipeRayout = null;
      target.gridView = null;

      this.target = null;
    }
  }
}
