// pages/me/me.js

const app = getApp();

Page({

  //页面初始数据
  data: {
    isShowUserName: false,
    userInfo: null
  },

  //button按钮事件 -> 获取用户信息
  onGotUserInfo: function(e) {
    if (e.detail.userInfo) {
      var user = e.detail.userInfo;
      this.setData({
        isShowUserName: true,
        userInfo: e.detail.userInfo,
      })
      user.openid = app.globalData.openid;
      app._saveUserInfo(user);
    } else{
      app._showSettingToast('登录需要允许授权');
    }
  },

  //跳转页面(我的订单页)
  goToMyOrder: function(){
    wx.navigateTo({
      url: '../myOrder/myOrder',
    })
  },

  //跳转页面(我的评论页)
  goToMyComment: function(){
    wx.navigateTo({
      url: '../mycomment/mycomment?type=1',
    })
  },

//个人信息页
  change(){
    wx.navigateTo({
      url: '../change/change',
    })
  },

//显示函数(页面显示)
onShow(options){
  console.log("个人show",options);
  var user = app.globalData.userInfo;
  if(user){
    this.setData({
      isShowUserName: true,
      userInfo: user,
    })
  }
},

//生命周期函数 -> 页面加载
onLoad: function(options){
  console.log("个人onLoad");
  var that = this;
  var user = app.globalData.userInfo;
  if(user){
    // that.setData({
    //   isShowUserName: true,
    //   userInfo: user,
    // })
  } else{
    // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
   // 所以此处加入 callback 以防止这种情况
    app.userInfoReadyCallback = res => {
      that.setData({
        userInfo: res.userInfo,
        isShowUserName: true
      })
    }
  }
}

})