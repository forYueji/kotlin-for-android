package com.example.kotlin.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hyp on 2018/3/27.
 * <p>
 * <p>Title: </p>
 * <p>
 * <p>Description: </p>
 * <p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>
 * <p>Company: </p>
 */

public class BaseWebView extends WebView {


    /**
     * 核对交易密码成功
     */
    public static final String CHECK_TRADE_PSD_SUCCESS = "1";
    /**
     * 核对交易密码失败
     */
    public static final String CHECK_TRADE_PSD_FAILURE = "0";

    private Map<String, Object> mEventMap = new HashMap<>();
    /**
     * 1:跳转到原生页面
     * 2:跳转到 webView 页面需要获取加密串
     * 3:跳转到webView 页面不需要加密串
     */
    private static final String KEY_NATIVE = "1";
    private static final String KEY_ENCRYPTED = "2";

    /**
     * webView 全屏监听事件
     */
    public interface OnWebFullscreenChangeListener {
        void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback);

        void onHideCustomView();
    }

    /**
     * webView loading 状态
     */
    public interface OnWebLoadingListener {
        void onProgressChange(int newProgress);

        void onLoadingSuccess();

        void onPageStarted();

        void onReceivedError();
    }

    /**
     * 设置 webView title 回调
     */
    public interface OnWebTitleListener {
        void onTitle(String title);
    }

    /**
     * 设置 webView 退出当前页面 协议监听器
     */
    public interface OnWebFinishListener {
        void onFinish();
    }

    /**
     * 抓取 WebView 点击事件 用户外部处理 UI
     */
    public interface OnWebClickListener {
        void onClose();

        void onPage(String page);
    }

    /**
     * webView onPageFinished
     */
    public interface OnWebPageFinishedListener {
        void onPageFinished(WebView view, String url);
    }

    /**
     * webView 弹窗 listener
     */
    public interface OnWebPopupWindowListener {
        void onTradePwd();
    }

    /**
     * webView 刷新
     */
    public interface OnWebRefreshListener {

        void onRefreshURL();
    }

    /**
     * webView 2.0 协议
     * toPage 协议中的 page 参数
     */
    public class WebKey {
        /**
         * 资讯
         */
        public static final String WEB_KEY_NEWS = "news";
        /**
         * 活动页面
         */
        public static final String WEB_KEY_ACTIVITY = "activity";
        /**
         * 基金-投资组合页面
         */
        public static final String WEB_KEY_PORTFOLIO = "portfolioList";
        /**
         * 转人工
         */
        public static final String WEB_KEY_ZRG = "ZRG";
        /**
         * 弹幕
         */
        public static final String WEB_KEY_TIP = "tip";
        /**
         * 绑卡开户
         */
        public static final String WEB_KEY_OPEN_BANK = "openbank";
        /**
         * 申购申请
         */
        public static final String WEB_KEY_DECLARE_FUND = "declareFund";
        /**
         * 认购申请
         */
        public static final String WEB_KEY_SUBSCRIBE_FUND = "subscribeFund";
        /**
         * 定投页面
         */
        public static final String WEB_KEY_FIXEDTERM_FUND = "fixedTermFund";
        /**
         * 基金详情
         */
        public static final String WEB_KEY_FUND_DETAIL = "fundDetail";
        /**
         * 人工客服
         */
        public static final String WEB_KEY_MANUAL = "Manual";
        /**
         * 智能客服
         */
        public static final String WEB_KEY_ROBOT = "Robot";
        /**
         * 我的资产
         */
        public static final String WEB_KEY_MY_ASSET = "MyAsset";
        /**
         * 活动中心
         */
        public static final String WEB_KEY_ACTIVITY_CENTTER = "activityCenter";
        /**
         * 组合申购
         */
        public static final String WEB_KEY_PORTFOLIO_BUY = "portfolioBuy";
        /**
         * 策略申购
         */
        public static final String URL_SLB_STRATEGY_BUY = "strategyBuy";
        /**
         * 组合详情
         */
        public static final String URL_SLB_PORTFOLIO_DETAIL = "portfolioDetail";
        /**
         * 策略查看详情
         */
        public static final String URL_SLB_STRATEGY_DETAIL = "strategyDetail";
        /**
         * 天玑智投 进入
         */
        public static final String WEB_KEY_ENTRY = "entry";
        /**
         * 基金转换
         */
        public static final String WEB_FUND_CONVERSION = "fundConversion";
        /**
         * 我的天玑智投 | 问卷结果
         */
        public static final String WEB_PHEGDA_RISK = "myPhegdaRisk";
        /**
         * 绑定手机号
         */
        public static final String WEB_BINDING_PHONE = "bindingPhone";
        /**
         * 完善个人资料
         */
        public static final String WEB_PERFECT_PERSONAL_DATA = "perfectPersonalData";

    }

    /**
     * webView 1.0 协议
     */
    private class Agreement {
        /**
         * 协议类型
         */
        public static final String URL_SLB = "shlb://";
        /**
         * 发送统计事件
         */
        public static final String URL_STAT = URL_SLB + "stat";
        /**
         * 下载图片
         */
        public static final String URL_SLB_DOWNLOAD = URL_SLB + "download";
        /**
         * 登录
         */
        public static final String URL_SLB_LOGIN = URL_SLB + "login";
        /**
         * 开户
         */
        public static final String URL_SLB_OPENBANK = URL_SLB + "openbank";
        /**
         * webView
         */
        public static final String URL_SLB_WEBVIEW = URL_SLB + "webview";
        /**
         * 外部浏览器
         */
        public static final String URL_SLB_JUMWEB = URL_SLB + "jumpweb";
        /**
         * 申购申请
         */
        public static final String URL_SLB_DECLARE_FUND = URL_SLB + "declareFund";
        /**
         * 认购申请
         */
        public static final String URL_SLB_SUBSCRIBE_FUND = URL_SLB + "subscribeFund";
        /**
         * 分享
         */
        public static final String URL_SLB_SHARE = URL_SLB + "share";
        /**
         * 活动
         */
        public static final String URL_SLB_PROMOTION = URL_SLB + "joinActivity";
        /**
         * 定投
         */
        public static final String URL_SLB_ADD_CAST = URL_SLB + "fixedTermFund";
        /**
         * 客服电话
         */
        public static final String URL_SLB_OPEN_CALL = URL_SLB + "call";
        /**
         * 基金详情
         */
        public static final String URL_SLB_FUND_DETAIL = URL_SLB + "fundDetail";
        /**
         * 预览图片
         */
        public static final String URL_SLB_PREVIEW = URL_SLB + "preview";
        /**
         * 退出当前页面协议
         */
        public static final String URL_SLB_FINISH = URL_SLB + "finish";
        /**
         * 页面跳转
         */
        public static final String URL_SLB_TO_PAGE = URL_SLB + "toPage";
        /**
         * 跳转到我的天机智投
         */
        public static final String URL_SLB_MY_PHEGDA = URL_SLB + "toMyPhegda";
        /**
         * 风险等级重新评测
         */
        public static final String URL_SLB_RISK = URL_SLB + "redoRisk";
        /**
         * 查看三个月收益率前三的基金
         */
        public static final String URL_SLB_RETE_THREE = URL_SLB + "reteThree";
        /**
         * 定投列表
         */
        public static final String URL_SLB_INVESTMENT_LIST = URL_SLB + "investmentList";
        /**
         * 交易确认
         */
        public static final String URL_SLB_TRADING_CONFIRM = URL_SLB + "transactionConfirm";
        /**
         * 刷新我的天玑智投页面
         */
        public static final String URL_SLB_REFRESH_PHEGDA = URL_SLB + "refreshMyPhegda";
        /**
         * 跳转至我的天玑智投页面
         */
        public static final String URL_SLB_TO_PHEGDA_PAGE = URL_SLB + "toPhegdaH5";
        /**
         * 验证交易密码弹窗协议
         */
        public static final String URL_SLB_TRADE_PASSWORD = URL_SLB + "tradePwd";
        /**
         * 刷新当前 url
         */
        public static final String URL_SLB_REFURBISH = URL_SLB + "refurbish";
    }

    private List<String> mURLList = new ArrayList<>();

    private WebViewProgressBar mProgressBar;

    /**
     * File upload callback for Android 5.0+
     */
    protected ValueCallback<Uri[]> mFileUploadCallbackSecond;
    protected ValueCallback<Uri> mFileUploadCallbackFirst;

    private Context mContext;

    /**
     * webView loading
     */
    private OnWebLoadingListener mWebLoadingListener;
    /**
     * webView click
     */
    private OnWebClickListener mWebClickListener;
    /**
     * webView title
     */
    private OnWebTitleListener mWebTitleListener;
    /**
     * webView finish
     */
    private OnWebFinishListener mWebFinishListener;
    /**
     * webView fullscreen change
     */
    private OnWebFullscreenChangeListener mWebFullscreenListener;
    /**
     * webView page listener
     */
    private OnWebPageFinishedListener mPageFinishedListener;
    /**
     * webView popup window
     */
    private OnWebPopupWindowListener mWebPopupWindowListener;
    /**
     * webView refresh
     */
    private OnWebRefreshListener mWebRefreshListener;


    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 这里指定访问 webViewStyle
     *
     * @param context
     * @param attrs
     */
    public BaseWebView(Context context, AttributeSet attrs) {
        this(context, attrs, Resources.getSystem().getIdentifier("webViewStyle", "attr", "android"));
    }

    public BaseWebView(Context context) {
        this(context, null);
    }

    private void init(Context context) {
        this.mContext = context;

        initProgressBar(context);

        WebSettings webSettings = getSettings();
        webSettings.setPluginState(WebSettings.PluginState.ON);
        /**
         * 设置编码格式
         */
        webSettings.setDefaultTextEncodingName("UTF-8");
        /**
         * 设置 WebView 支持缩放
         */
        webSettings.setSupportZoom(true);//支持缩放
        /**
         * 设置 WebView 使用内置缩放机制时，是否展现在屏幕缩放控件上
         */
        webSettings.setDisplayZoomControls(false);
        /**
         * 启用 WebView 内置缩放功能
         */
        webSettings.setBuiltInZoomControls(true);
        /**
         * 纵向滑动轴
         */
        setVerticalScrollBarEnabled(true);
        /**
         * 横向滑动轴
         */
        setHorizontalScrollBarEnabled(false);
        /**
         * 设置在 WebView 内部是否允许访问文件
         */
        webSettings.setAllowFileAccess(true);
        /**
         * 设置 WebView 支持可任意比例缩放
         */
        webSettings.setUseWideViewPort(true);
        /**
         * 设置 Webview 启用javascript支持 用于访问页面中的javascript
         */
        webSettings.setJavaScriptEnabled(true);
        /**
         * 网络图片块
         */
        webSettings.setBlockNetworkImage(false);
        /**
         * 设置自适应屏幕
         */
        webSettings.setLoadWithOverviewMode(true);
        /**
         * 设置是否支持新窗口 设置为true，必须要重写WebChromeClient的onCreateWindow方法
         */
        webSettings.setSupportMultipleWindows(false);
        webSettings.setLoadsImagesAutomatically(true);
        /**
         * 设置WebView是否通过手势触发播放媒体, 默认是true ,需要手势触发
         */
        webSettings.setMediaPlaybackRequiresUserGesture(false);
        /**
         * 设置 通过 js 打开新窗口
         */
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        /**
         * 设置webView 获取焦点
         */
        requestFocus();

        setWebChromeClient(new SHLBWebChromeClient());
        setWebViewClient(new SHLBWebViewClient());
    }

    /**
     * 初始化 ProgressBar
     *
     * @param context
     */
    private void initProgressBar(Context context) {
        mProgressBar = new WebViewProgressBar(context);
        mProgressBar.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        /**
         * 刚开始时候进度条不可见
         */
        mProgressBar.setVisibility(GONE);

        addView(mProgressBar);
    }

    public WebViewProgressBar getProgressBar() {
        if (null == mProgressBar) {
            initProgressBar(mContext);
        }
        return mProgressBar;
    }

    /**
     * webView 弹窗监听
     *
     * @param listener
     */
    public void setOnWebPopupWindowListener(OnWebPopupWindowListener listener) {
        mWebPopupWindowListener = listener;
    }

    public void setPageFinishedListener(OnWebPageFinishedListener finishedListener) {
        mPageFinishedListener = finishedListener;
    }

    /**
     * webView 全屏监听
     */
    public void setFullscreenListener(OnWebFullscreenChangeListener listener) {
        mWebFullscreenListener = listener;
    }

    /**
     * webView 退出协议
     *
     * @param listener
     */
    public void setOnWebFinishListener(OnWebFinishListener listener) {
        this.mWebFinishListener = listener;
    }

    /**
     * 内置webView loading 接口
     *
     * @param listener
     */
    public void setOnWebLoadingListener(OnWebLoadingListener listener) {
        this.mWebLoadingListener = listener;
    }

    /**
     * 内置webView 点击 接口 | 主要用户首页弹窗 当用户点击弹窗 协议时取消 弹窗
     *
     * @param listener
     */
    public void setOnWebClickListener(OnWebClickListener listener) {
        this.mWebClickListener = listener;
    }

    /**
     * webView title 监听
     *
     * @param listener
     */
    public void setOnWebTitleListener(OnWebTitleListener listener) {
        this.mWebTitleListener = listener;
    }

    /**
     * webView refresh 监听
     *
     * @param listener
     */
    public void setOnWebRefreshListener(OnWebRefreshListener listener) {
        this.mWebRefreshListener = listener;
    }

    /**
     * open File.
     * used for custom service page.
     *
     * @param fileUploadCallbackFirst
     * @param fileUploadCallbackSecond
     */
    @SuppressLint("NewApi")
    protected void openFileInput(ValueCallback<Uri> fileUploadCallbackFirst, ValueCallback<Uri[]> fileUploadCallbackSecond) {
        uploadPhotoNull();
        mFileUploadCallbackFirst = fileUploadCallbackFirst;
        mFileUploadCallbackSecond = fileUploadCallbackSecond;

        initPhotoPicker();
    }

    /**
     * 初始化 图片选择器
     */
    private void initPhotoPicker() {

    }

    /**
     * 取消文件回调
     */
    private void uploadPhotoNull() {
        if (mFileUploadCallbackFirst != null) {
            mFileUploadCallbackFirst.onReceiveValue(null);
            mFileUploadCallbackFirst = null;
        } else if (mFileUploadCallbackSecond != null) {
            mFileUploadCallbackSecond.onReceiveValue(null);
            mFileUploadCallbackSecond = null;
        }
    }

    /**
     * 发送文件回调
     *
     * @param path
     */
    private void uploadPhoto(String path) {
        if (mFileUploadCallbackFirst != null) {
            mFileUploadCallbackFirst.onReceiveValue(Uri.parse(path));
            mFileUploadCallbackFirst = null;
        } else if (mFileUploadCallbackSecond != null) {
            Uri[] dataUris = null;
            try {
                if (!TextUtils.isEmpty(path)) {
                    dataUris = new Uri[]{Uri.parse(path)};
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            mFileUploadCallbackSecond.onReceiveValue(dataUris);
            mFileUploadCallbackSecond = null;
        }
    }

    /**
     * Activity 方法 用户内部调用
     *
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

    }

    /**
     * 处理 webView 网站图标，网站title，加载进度 发送文件等
     */
    private class SHLBWebChromeClient extends WebChromeClient {

        @SuppressWarnings("unused")
        public void openFileChooser(ValueCallback<Uri> uploadMsg) {
            openFileChooser(uploadMsg, null);
        }

        /**
         * file upload callback (Android 3.0 (API level 11) -- Android 4.0 (API level 15)) (hidden method)
         *
         * @param uploadMsg
         * @param acceptType
         */
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
            openFileChooser(uploadMsg, acceptType, null);
        }

        /**
         * file upload callback (Android 4.1 (API level 16) -- Android 4.3 (API level 18)) (hidden method)
         *
         * @param uploadMsg
         * @param acceptType
         * @param capture
         */
        @SuppressWarnings("unused")
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
            openFileInput(uploadMsg, null);
        }

        /**
         * file upload callback (Android 5.0 (API level 21) -- current) (public method)
         *
         * @param webView
         * @param filePathCallback
         * @param fileChooserParams
         * @return
         */
        @SuppressWarnings("all")
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            boolean allowMultiple = fileChooserParams.getMode() == FileChooserParams.MODE_OPEN_MULTIPLE;
            openFileInput(null, filePathCallback);
            return true;
        }

        /**
         * 获取网页title
         *
         * @param view
         * @param title
         */
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            /**
             * 获取webView | title
             */
            if (mWebTitleListener != null) {
                mWebTitleListener.onTitle(title);
            }
        }

        /**
         * 获取网页加载进度
         *
         * @param view
         * @param newProgress
         */
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            /**
             * webView laoding 进度
             */
            if (mWebLoadingListener != null) {
                mWebLoadingListener.onProgressChange(newProgress);
            }
        }

        @Override
        public View getVideoLoadingProgressView() {
            if (null == mContext) {
                return super.getVideoLoadingProgressView();
            }
            FrameLayout frameLayout = new FrameLayout(mContext);
            frameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return frameLayout;
        }


        /**
         * webView 全屏回调
         *
         * @param view
         * @param callback
         */
        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            super.onShowCustomView(view, callback);
            try {
                if (mWebFullscreenListener != null) {
                    mWebFullscreenListener.onShowCustomView(view, callback);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * webView 取消全屏回调
         */
        @Override
        public void onHideCustomView() {
            super.onHideCustomView();
            if (mWebFullscreenListener != null) {
                mWebFullscreenListener.onHideCustomView();
            }
        }
    }

    /**
     * 处理 WebView处理各种通知、请求事件
     */
    private class SHLBWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // 开始loadingWebView
            super.onPageStarted(view, url, favicon);
            if (mWebLoadingListener != null) {
                mWebLoadingListener.onPageStarted();
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (null != mPageFinishedListener) {
                mPageFinishedListener.onPageFinished(view, url);
            }
            // 加载webView 加载完成
            if (mWebLoadingListener != null) {
                mWebLoadingListener.onLoadingSuccess();
            }
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            // 加载webView失败
            super.onReceivedError(view, errorCode, description, failingUrl);
            if (mWebLoadingListener != null) {
                mWebLoadingListener.onReceivedError();
            }
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (TextUtils.isEmpty(url)) {
                return false;
            }
            /**
             * 如果获取的json 头包含了协议标示 , 则执行协议部分代码
             */
            if (url.startsWith(Agreement.URL_SLB)) {
                return true;
            }
            addURLList(url);
            return false;
        }
    }

    public void addURLList(String url) {
        mURLList.add(url);
    }

    public List<String> getURLList() {
        return mURLList;
    }

    public void removeURLList() {
        mURLList.clear();
    }

    private String mSavedLastUrl = "";

    private void onStartActivity(Intent intent) {
        mContext.startActivity(intent);
    }

    /**
     * 设置 ProgressBar 开始动画
     *
     * @param newProgress
     * @param progressBar
     * @param currentProgress
     */
    public void startProgressAnimation(int newProgress, WebViewProgressBar progressBar, int currentProgress) {
        ObjectAnimator animator = ObjectAnimator.ofInt(progressBar, "progress", currentProgress, newProgress);
        animator.setDuration(200);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
    }

    /**
     * 设置 ProgressBar 结束动画
     *
     * @param progress
     * @param mProgressBar
     * @return
     */
    public void startDismissAnimation(final int progress, final WebViewProgressBar mProgressBar) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(mProgressBar, "alpha", 0.0f, 0.0f);
        anim.setDuration(2500);  // 动画时长
        anim.setInterpolator(new DecelerateInterpolator());
        // 添加动画进度监听器
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float fraction = valueAnimator.getAnimatedFraction();
                int offset = 100 - progress;
                mProgressBar.setProgress((int) (progress + offset * fraction));
            }
        });
        anim.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                // 动画结束
                mProgressBar.setProgress(100);
                mProgressBar.setVisibility(View.GONE);
            }
        });
        anim.start();
    }
}
