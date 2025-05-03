package com.daniel.pulsingbutton;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PulsingLoadingButton extends FrameLayout {

    public ImageView getLoadingIconView() {
        return iconView;
    }

    public enum State { IDLE, LOADING, SUCCESS }

    private State currentState = State.IDLE;
    private TextView textView;
    private ImageView iconView;
    private Paint pulsePaint;
    private float pulseRadius = 0f;
    private ValueAnimator pulseAnimator;
    private int successIconRes = 0;
    private int loadingIconRes = 0;

    public PulsingLoadingButton(Context context) {
        super(context);
        init(context, null);
    }

    public PulsingLoadingButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PulsingLoadingButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        setWillNotDraw(false);
        setBackgroundResource(android.R.drawable.btn_default);

        int pulseColor = 0x552196F3;
        String buttonText = "Submit";

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PulsingLoadingButton);
            buttonText = a.getString(R.styleable.PulsingLoadingButton_buttonText);
            pulseColor = a.getColor(R.styleable.PulsingLoadingButton_pulseColor, pulseColor);
            loadingIconRes = a.getResourceId(R.styleable.PulsingLoadingButton_loadingIcon, 0);
            successIconRes = a.getResourceId(R.styleable.PulsingLoadingButton_successIcon, 0);
            a.recycle();
        }

        // Pulse circle paint
        pulsePaint = new Paint();
        pulsePaint.setColor(pulseColor);
        pulsePaint.setStyle(Paint.Style.FILL);

        // TextView setup
        textView = new TextView(context);
        textView.setText(buttonText != null ? buttonText : "Submit");
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(0xFFFFFFFF);

        // Icon setup
        iconView = new ImageView(context);
        iconView.setVisibility(GONE);
        if (loadingIconRes != 0) {
            iconView.setImageResource(loadingIconRes);
        }

        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        addView(textView, lp);
        addView(iconView, lp);

        setupPulseAnimation();
    }

    private void setupPulseAnimation() {
        pulseAnimator = ValueAnimator.ofFloat(0f, 1f);
        pulseAnimator.setDuration(1000);
        pulseAnimator.setRepeatCount(ValueAnimator.INFINITE);
        pulseAnimator.setInterpolator(new LinearInterpolator());
        pulseAnimator.addUpdateListener(animation -> {
            pulseRadius = (float) animation.getAnimatedValue();
            invalidate();
        });
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        if (currentState == State.LOADING) {
            float maxRadius = getWidth() * 0.6f;
            float radius = pulseRadius * maxRadius;
            canvas.drawCircle(getWidth() / 2f, getHeight() / 2f, radius, pulsePaint);
        }
    }

    public void setState(State state) {
        this.currentState = state;

        switch (state) {
            case IDLE:
                textView.setVisibility(VISIBLE);
                textView.setText(R.string.submit);
                iconView.setVisibility(GONE);
                pulseAnimator.cancel();
                break;

            case LOADING:
                textView.setVisibility(GONE);
                iconView.setVisibility(VISIBLE);
                if (loadingIconRes != 0) {
                    iconView.setImageResource(loadingIconRes);
                } else {
                    iconView.setImageDrawable(null); // clear any fallback
                }
                pulseAnimator.start();
                break;

            case SUCCESS:
                textView.setVisibility(GONE);
                iconView.setVisibility(VISIBLE);
                if (successIconRes != 0) {
                    iconView.setImageResource(successIconRes);
                } else {
                    iconView.setImageResource(android.R.drawable.checkbox_on_background);
                }
                pulseAnimator.cancel();
                break;
        }

        invalidate();
    }
}
