package om.gradient;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;


public class GradientTextView extends android.support.v7.widget.AppCompatTextView {
    private int startColor=0xFFE64433;
    private int endColor=0xFFFDB628;
    private int unStartColor=0xFFE64433;
    private int unEndColor=0xFFFDB628;

    private int textWidth,textHight;
    private LinearGradient shader;
    private LinearGradient unShader;
    private boolean isSelected=true;


    public GradientTextView(Context context) {
        super(context);
    }

    public GradientTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public GradientTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }
    private void init(Context context, @Nullable AttributeSet attrs){
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.GradientTextView);
        startColor = a.getColor(R.styleable.GradientTextView_gtv_startColor, startColor);
        endColor = a.getColor(R.styleable.GradientTextView_gtv_endColor, endColor);
        unStartColor= a.getColor(R.styleable.GradientTextView_gtv_unStartColor, unStartColor);
        unEndColor= a.getColor(R.styleable.GradientTextView_gtv_unEndColor, unEndColor);
        isSelected=a.getBoolean(R.styleable.GradientTextView_gtv_isSelected,isSelected);
        a.recycle();
    }

    public void init(int startColor,int endColor,int unStartColor,int unEndColor,boolean isSelected){
        this.startColor=startColor;
        this.endColor=endColor;
        this.unStartColor=unStartColor;
        this.unEndColor=unEndColor;
        this.isSelected=isSelected;
        setGtvSelected(isSelected);
    }

    public void setGtvSelected(boolean selected) {
        isSelected = selected;
        setLinearGradient();
        invalidate();
        setSelected(isSelected);
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (textWidth == 0) {
            textWidth = getMeasuredWidth();
        }
        if (textHight==0){
            textHight=getMeasuredHeight();
        }
        if (shader == null) {
            float[] position = {0f, 0.40f, 0.6f, 1.0f};//颜色渐变位置的数
            shader = new LinearGradient(0, 0, textWidth, 0,
                    new int[]{startColor,startColor,endColor,endColor}, position, Shader.TileMode.CLAMP);
        }
        if (unShader == null) {
            float[] position = {0f, 0.4f, 0.6f, 1.0f};//颜色渐变位置的数
            unShader = new LinearGradient(0, 0, textWidth, 0,
                    new int[]{unStartColor,unStartColor,unEndColor,unEndColor}, position, Shader.TileMode.CLAMP);
        }
        setLinearGradient();
    }

    private void setLinearGradient(){
        if (textWidth > 0) {
            //得到父类中写字的那支笔，并套上线性渲染器
            TextPaint paint = getPaint();
            //设置渐变背景
            if (isSelected) {
                paint.setShader(shader);
            }else {
                paint.setShader(unShader);
            }
        }
    }

}
