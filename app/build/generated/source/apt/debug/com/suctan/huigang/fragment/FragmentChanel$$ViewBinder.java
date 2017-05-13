// Generated code from Butter Knife. Do not modify!
package com.suctan.huigang.fragment;

import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import cn.bingoogolapple.badgeview.BGABadgeRadioButton;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class FragmentChanel$$ViewBinder<T extends FragmentChanel> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends FragmentChanel> implements Unbinder {
    protected T target;

    protected InnerUnbinder(T target, Finder finder, Object source) {
      this.target = target;

      target.share_jz = finder.findRequiredViewAsType(source, 2131558728, "field 'share_jz'", BGABadgeRadioButton.class);
      target.share_dg = finder.findRequiredViewAsType(source, 2131558729, "field 'share_dg'", BGABadgeRadioButton.class);
      target.share_sfc = finder.findRequiredViewAsType(source, 2131558730, "field 'share_sfc'", BGABadgeRadioButton.class);
      target.share_dskd = finder.findRequiredViewAsType(source, 2131558731, "field 'share_dskd'", BGABadgeRadioButton.class);
      target.share_jsxh = finder.findRequiredViewAsType(source, 2131558733, "field 'share_jsxh'", BGABadgeRadioButton.class);
      target.share_phlr = finder.findRequiredViewAsType(source, 2131558734, "field 'share_phlr'", BGABadgeRadioButton.class);
      target.share_esjy = finder.findRequiredViewAsType(source, 2131558735, "field 'share_esjy'", BGABadgeRadioButton.class);
      target.share_jqqd = finder.findRequiredViewAsType(source, 2131558736, "field 'share_jqqd'", BGABadgeRadioButton.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.share_jz = null;
      target.share_dg = null;
      target.share_sfc = null;
      target.share_dskd = null;
      target.share_jsxh = null;
      target.share_phlr = null;
      target.share_esjy = null;
      target.share_jqqd = null;

      this.target = null;
    }
  }
}
