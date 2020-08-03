# 渐变框文本框和渐变文本
### 提示文本输入框
##### 话不多说，先展示效果
![image](https://github.com/fengjian2015/gradient/blob/master/gradient/gifhome_626x1280_5s.gif)

#####引入方式
 implementation 'com.fj:gradient:1.0.0'

 #####示例
 ```
 <om.gradient.GradientTextView
        android:id="@+id/gtv"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:text="Hello World!"
        android:textSize="18sp"
        app:gtv_startColor="#dd99ff"
        app:gtv_endColor="#9900e6"
        app:gtv_unEndColor="#ffb3d1"
        app:gtv_unStartColor="#ffb3d1"
        app:gtv_isSelected="true"
         />
    <om.gradient.RoundRainbowRelayoutLayout
        android:id="@+id/rrrl"
        android:layout_width="match_parent"
        android:layout_height="48dip"
        app:rrv_textSize="18sp"
        app:rrv_startColor="#dd99ff"
        app:rrv_endColor="#9900e6"
        app:rrv_width="1dp"
        app:rrv_radius="20dp"
        app:rrv_unEndColor="#ffb3d1"
        app:rrv_unStartColor="#ffb3d1"
        app:rrv_text="设置"
        app:rrv_isSelected="true"
        android:layout_marginRight="10dp"
        android:layout_marginLeft="10dp"
        android:layout_marginTop="10dp"
        />
 ```

##### RoundRainbowRelayoutLayout参数
| attaes name        | 参数   |  用途  |
| --------   | -----:  | :----:  |
| rrv_radius      | dimension |   圆框角度     |
| rrv_width        |   dimension   |  圆框线条宽度  |
| rrv_startColor        |    color    |  圆框选中开始颜色  |
| rrv_endColor        |    color    |  圆框选中结束颜色  |
| rrv_unStartColor        |    color    |  圆框未选中开始颜色 |
| rrv_unEndColor        |    color    |  圆框未选中结束颜色  |
| rrv_isSelected        |    boolean    |  是否选中  |
| rrv_text        |    string    |  文本  |
| rrv_textSize        |    dimension    |  文本大小 |
| rrv_selected_img        |    reference    |  选中图片  |

##### GradientTextView
| attaes name        | 参数   |  用途  |
| --------   | -----:  | :----:  |
| gtv_startColor      | color |   选中开始颜色     |
| gtv_endColor        |   color   |  选中结束颜色  |
| gtv_unStartColor        |    color    |  未选中开始颜色  |
| gtv_unEndColor        |    color    |  未选中结束颜色  |
| gtv_isSelected        |    boolean    |  是否选中  |

