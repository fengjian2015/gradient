package om.gradient;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * 相对于RoundRainbowTextView做了优化，避免二次绘制字体镂空
 */
public class RoundRainbowRelayoutLayout extends RelativeLayout {
    private static final float BORDER_WIDTH = 1f;//dp
    private static final float BORDER_RADIUS = 3f;//dp
    private boolean drawBorder = true;
    private float borderWidth = BORDER_WIDTH;
    private float borderRadius = BORDER_RADIUS;
    private int startColor=0xFFE64433;
    private int endColor=0xFFFDB628;
    private int unStartColor=0xFFE64433;
    private int unEndColor=0xFFFDB628;
    private boolean isSelected=true;

    private int ViewWidth,ViewHight;
    private LinearGradient shader;
    private LinearGradient unShader;
    private Rect rect;
    private RectF rectF;
    private Paint paint;
    private String text;
    private int textSize;
    private GradientTextView gradientTextView;
    private ImageView imageView;
    private int img=-1;//选中图片样式



    public RoundRainbowRelayoutLayout(Context context) {
        super(context);
    }

    public RoundRainbowRelayoutLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public RoundRainbowRelayoutLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs){
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundRainbowRelayoutLayout);

        startColor = a.getColor(R.styleable.RoundRainbowRelayoutLayout_rrv_startColor, startColor);
        endColor = a.getColor(R.styleable.RoundRainbowRelayoutLayout_rrv_endColor, endColor);
        unStartColor = a.getColor(R.styleable.RoundRainbowRelayoutLayout_rrv_unStartColor, unStartColor);
        unEndColor = a.getColor(R.styleable.RoundRainbowRelayoutLayout_rrv_unEndColor, unEndColor);
        isSelected=a.getBoolean(R.styleable.RoundRainbowRelayoutLayout_rrv_isSelected,isSelected);
        borderWidth = a.getDimensionPixelSize(R.styleable.RoundRainbowRelayoutLayout_rrv_width, (int) borderWidth);
        borderRadius = a.getDimensionPixelSize(R.styleable.RoundRainbowRelayoutLayout_rrv_radius, (int) borderRadius);
        text=a.getString(R.styleable.RoundRainbowRelayoutLayout_rrv_text);
        textSize=a.getDimensionPixelSize(R.styleable.RoundRainbowRelayoutLayout_rrv_textSize,textSize);
        img=a.getResourceId(R.styleable.RoundRainbowRelayoutLayout_rrv_selected_img,img);
        this.drawBorder = borderRadius > 0;
        setBackgroundColor(Color.TRANSPARENT);
        setText(text);
        initSelectedImage();
        a.recycle();
    }

    public void setRrvSelected(boolean selected) {
        isSelected = selected;
        setBackgroundColor(Color.TRANSPARENT);
        setLinearGradient();
        if (gradientTextView!=null){
            gradientTextView.setGtvSelected(selected);
        }
        setImg();
        invalidate();
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    public void setText(String text){
        this.text=text;
        if (gradientTextView==null){
            initGradientTextView();
        }else {
            gradientTextView.setText(text);
        }
    }

    private void setImg(){
        if (imageView!=null){
            if (isSelected){
                imageView.setVisibility(VISIBLE);
            }else {
                imageView.setVisibility(GONE);
            }
        }
    }

    private void initGradientTextView(){
        if (!TextUtils.isEmpty(text)){
            gradientTextView=new GradientTextView(getContext());
            gradientTextView.setGravity(Gravity.CENTER);
            RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
            gradientTextView.setLayoutParams(layoutParams);
            gradientTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);
            gradientTextView.setText(text);
            gradientTextView.init(startColor,endColor,unStartColor,unEndColor,isSelected);
            addView(gradientTextView);
        }
    }

    private void initSelectedImage(){
        if (img!=-1){
            imageView=new ImageView(getContext());
            RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,RelativeLayout.TRUE);
            imageView.setLayoutParams(layoutParams);
            if (isSelected){
                imageView.setVisibility(VISIBLE);
            }else {
                imageView.setVisibility(GONE);
            }
            imageView.setBackgroundResource(img);
            addView(imageView);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (ViewWidth == 0) {
            ViewWidth = getMeasuredWidth();
        }
        if (ViewHight==0){
            ViewHight=getMeasuredHeight();
        }
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (ViewWidth == 0) {
            ViewWidth = getMeasuredWidth();
        }
        if (ViewHight==0){
            ViewHight=getMeasuredHeight();
        }
        if (shader == null) {
            float[] position = {0f, 0.40f, 0.6f, 1.0f};//颜色渐变位置的数
            shader = new LinearGradient(0, 0, ViewWidth, 0,
                    new int[]{startColor,startColor,endColor,endColor}, position, Shader.TileMode.CLAMP);
        }
        if (unShader == null) {
            float[] position = {0f, 0.40f, 0.6f, 1.0f};//颜色渐变位置的数
            unShader = new LinearGradient(0, 0, ViewWidth, 0,
                    new int[]{unStartColor,unStartColor,unEndColor,unEndColor}, position, Shader.TileMode.CLAMP);
        }
        setLinearGradient();
    }

    private void setLinearGradient(){
        if (ViewWidth > 0) {
            getRrvPaint();
            //设置渐变背景
            if (isSelected) {
                paint.setShader(shader);
            }else {
                paint.setShader(unShader);
            }
        }
    }

    private void getRrvPaint(){
        if (paint==null){
            paint=new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(borderWidth);
            paint.setAntiAlias(true);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制渐变圆角边框
        if (ViewWidth > 0 && drawBorder) {
            canvas.save();
            getRrvPaint();
            if (rect == null) {
                rect = new Rect(dip2px(getContext(),1), dip2px(getContext(),1), (ViewWidth-dip2px(getContext(),1)), (ViewHight-dip2px(getContext(),1)));
            }
//            canvas.getClipBounds(rect);
            if (rectF == null) {
                rectF = new RectF(rect);
            }
            float radius =borderRadius;
            canvas.drawRoundRect(rectF, radius, radius, paint);
            canvas.restore();
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
