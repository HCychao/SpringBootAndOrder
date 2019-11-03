//JS
var app = getApp()

Page({
  data: {
    // 顶部菜单切换
    navbar: ['全部评价', "我的评价"],
    // 默认选中菜单
    currentTab: 0,
    list: []
  },
  //顶部tab切换
  navbarTap: function(e) {
    let index = e.currentTarget.dataset.idx;
    this.setData({
      currentTab: index
    })
    if (index == 1) {
      this.getMyCommentList();
    } else {
      this.getCommentList();
    }

  },
  onLoad(options) {
    let type = options.type;
    if (type == 1) {
      this.getMyCommentList();
      this.setData({
        currentTab: 1
      })
    } else {
      this.getCommentList();

    }
  },
  //获取所有评论列表
  getCommentList() {
    let that = this;
    //请求自己后台获取用户openid
    wx.request({
      url: app.globalData.baseUrl + '/commentList',
      success: function(res) {
        if (res && res.data && res.data.data && res.data.data.length > 0) {
          let dataList = res.data.data;
          console.log(dataList)
          that.setData({
            list: dataList
          })
        } else {
          that.setData({
            list: []
          })
        }
      }
    })
  },

  //获取我的所有评论列表
  getMyCommentList() {
    let that = this;
    let openid = app._checkOpenid();
    if (!openid) {
      return;
    }
    //请求自己后台获取用户openid
    wx.request({
      url: app.globalData.baseUrl + '/userCommentList',
      data: {
        openid: openid
      },
      success: function(res) {
        if (res && res.data && res.data.data && res.data.data.length > 0) {
          let dataList = res.data.data;
          console.log(dataList)
          that.setData({
            list: dataList
          })
        } else {
          that.setData({
            list: []
          })
        }
      }
    })
  },
})