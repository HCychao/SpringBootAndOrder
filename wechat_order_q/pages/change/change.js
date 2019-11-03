// change.js

const app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    username: '',
    phone: '',
    tablenumber: '',
    number: ''
  },

  bindinputusername: function(e) {
    this.setData({
      username: e.detail.value
    })
  },

  bindinputphone: function(e) {
    this.setData({
      phone: e.detail.value
    })
  },

  bindinputtablenumber: function(e) {
    this.setData({
      tablenumber: e.detail.value
    })
  },

  bindinputrenshu: function(e) {
    this.setData({
      number: e.detail.value
    })
  },

  /**
   * 修改个人信息
   */
  formSubmit: function() {
    var that = this;
    //如果 openid 不存在 ，就重新请求接口获取openid
    var openid = app.globalData.openid;
    if (openid === null || openid === undefined) {
      app.getOpenid();
      //这里提示失败原因
      wx.showToast({
        title: '您还没有登陆！',
        duration: 1500
      })
      return;
    }

    let username = that.data.username;
    let phone = that.data.phone;
    let tablenumber = that.data.tablenumber;
    console.log(tablenumber);
    let number = that.data.number;
    console.log(number);

    if (username === '') {
      wx.showToast({
        title: '用户名不能为空',
        icon: 'none'
      })
      return;
    }
    if (phone === '') {
      wx.showToast({
        title: '手机号不能为空',
        icon: 'none'
      })
      return;
    }

    wx.request({
      url: app.globalData.baseUrl + '/user/save',
      method: "POST",
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      data: {
        openid: openid,
        username: username,
        phone: phone,
        usertable: tablenumber,
        usernumber: number
      },
      success: function(res) {
        wx.showToast({
          title: '修改成功',
        })
        app._getMyUserInfo();
        wx.switchTab({
          url: '../index/index',
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    let that = this;
    var openid = app.globalData.openid;
    if (openid === null || openid === undefined) {
      app.getOpenid();
      wx.showToast({
        title: '您还没有登陆！',
        duration: 1500
      })
      return;
    }
    if (app.globalData.userInfo) {
      console.log(app.globalData.userInfo);
      that.setData({
        username: app.globalData.userInfo.realname,
        phone: app.globalData.userInfo.realphone,
        tablenumber: app.globalData.userInfo.realzhuohao,
        number: app.globalData.userInfo.realrenshu
      })
    }
  },

})