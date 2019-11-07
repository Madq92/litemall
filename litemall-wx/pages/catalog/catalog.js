var util = require('../../utils/util.js');
var api = require('../../config/api.js');

Page({
  data: {
    categoryList: [],
    currentCategory: null,
    currentCategoryGoodsList: {},
  },
  onLoad: function(options) {
    this.getCatalog(options.id);
  },
  onPullDownRefresh() {
    wx.showNavigationBarLoading() //在标题栏中显示加载
    this.getCatalog();
    wx.hideNavigationBarLoading() //完成停止加载
    wx.stopPullDownRefresh() //停止下拉刷新
  },
  getCatalog: function(id) {
    let that = this;
    wx.showLoading({
      title: '加载中...',
    });
    if(!id){
      id = "";
    }
    util.request(api.CatalogV2List, {
      categoryId: id
    }).then(function(res) {
      that.setData({
        categoryList: res.data.categoriesList,
        currentCategoryGoodsList: res.data.currentGoodsList,
        currentCategory: res.data.currentCategory,
      });
      wx.hideLoading();
    });
  },
  onReady: function() {
    // 页面渲染完成
  },
  onShow: function() {
    // 页面显示
  },
  onHide: function() {
    // 页面隐藏
  },
  onUnload: function() {
    // 页面关闭
  },
  getCurrentCategory: function (id) {
    let that = this;
    util.request(api.CatalogV2List, {
      categoryId: id
    }).then(function (res) {
        that.setData({
          categoryList: res.data.categoriesList,
          currentCategoryGoodsList: res.data.currentGoodsList,
          currentCategory: res.data.currentCategory,
        });
      });
  },
  switchCate: function(event) {
    var that = this;
    var currentTarget = event.currentTarget;
    if (this.data.currentCategory.id == event.currentTarget.dataset.id) {
      return false;
    }

    this.getCurrentCategory(event.currentTarget.dataset.id);
  }
})