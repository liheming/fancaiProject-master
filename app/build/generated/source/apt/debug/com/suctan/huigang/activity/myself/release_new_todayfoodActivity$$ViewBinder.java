// Generated code from Butter Knife. Do not modify!
package com.suctan.huigang.activity.myself;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import com.sevenheaven.segmentcontrol.SegmentControl;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class release_new_todayfoodActivity$$ViewBinder<T extends release_new_todayfoodActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends release_new_todayfoodActivity> implements Unbinder {
    protected T target;

    protected InnerUnbinder(T target, Finder finder, Object source) {
      this.target = target;

      target.new_food_idea = finder.findRequiredViewAsType(source, 2131558906, "field 'new_food_idea'", LinearLayout.class);
      target.new_food_back = finder.findRequiredViewAsType(source, 2131558900, "field 'new_food_back'", ImageView.class);
      target.imv_addFood = finder.findRequiredViewAsType(source, 2131558903, "field 'imv_addFood'", ImageView.class);
      target.edt_addFood_name = finder.findRequiredViewAsType(source, 2131558901, "field 'edt_addFood_name'", EditText.class);
      target.edt_addFood_moneny = finder.findRequiredViewAsType(source, 2131558902, "field 'edt_addFood_moneny'", EditText.class);
      target.edt_addFood_detail = finder.findRequiredViewAsType(source, 2131558904, "field 'edt_addFood_detail'", EditText.class);
      target.btn_addFood_comfirm = finder.findRequiredViewAsType(source, 2131558907, "field 'btn_addFood_comfirm'", Button.class);
      target.segmentViewControlChoose = finder.findRequiredViewAsType(source, 2131558905, "field 'segmentViewControlChoose'", SegmentControl.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.new_food_idea = null;
      target.new_food_back = null;
      target.imv_addFood = null;
      target.edt_addFood_name = null;
      target.edt_addFood_moneny = null;
      target.edt_addFood_detail = null;
      target.btn_addFood_comfirm = null;
      target.segmentViewControlChoose = null;

      this.target = null;
    }
  }
}
