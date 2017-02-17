# Qzone-master
高仿QQ空间好友点赞功能，抽取出RxPraisefriendsView依赖使用</br>
##效果图：</br>
![image](https://github.com/razerdp/PraiseWidget/blob/master/img/praise widget.gif)
##使用方法：</br>
在布局配置控件后，使用setData方法传入消息数据，根据需求自行定义</br>
##属性：(attrs.xml)</br>
```html
     <!--显示点赞控件-->
      <declare-styleable name="RxPraisefriendsView">
      <attr name="click_bg_color" format="color"/>//点击的背景色，默认全透明
        <attr name="font_color" format="color"/>//文字颜色，默认蓝
        <attr name="font_size" format="dimension"/>//文字大小，默认16sp
        <attr name="like_icon" format="reference" />//点赞图标，默认一个蓝色大拇指
      </declare-styleable>
```
