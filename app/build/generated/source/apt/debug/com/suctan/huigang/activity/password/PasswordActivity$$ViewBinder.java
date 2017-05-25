// Generated code from Butter Knife. Do not modify!
package com.suctan.huigang.activity.password;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class PasswordActivity$$ViewBinder<T extends PasswordActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends PasswordActivity> implements Unbinder {
    protected T target;

    protected InnerUnbinder(T target, Finder finder, Object source) {
      this.target = target;

      target.btn_psw = finder.findRequiredViewAsType(source, 2131558595, "field 'btn_psw'", Button.class);
      target.Pswedit_phone = finder.findRequiredViewAsType(source, 2131558592, "field 'Pswedit_phone'", EditText.class);
      target.pswedit_old = finder.findRequiredViewAsType(source, 2131558593, "field 'pswedit_old'", EditText.class);
      target.pswedit_new = finder.findRequiredViewAsType(source, 2131558594, "field 'pswedit_new'", EditText.class);
      target.psw_back = finder.findRequiredViewAsType(source, 2131558591, "field 'psw_back'", ImageView.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.btn_psw = null;
      target.Pswedit_phone = null;
      target.pswedit_old = null;
      target.pswedit_new = null;
      target.psw_back = null;

      this.target = null;
    }
  }
}
