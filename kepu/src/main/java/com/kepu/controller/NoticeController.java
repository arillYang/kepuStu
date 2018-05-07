package com.kepu.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kepu.constant.ResultConstant;
import com.kepu.pojo.KePuResult;
import com.kepu.pojo.StUser;
import com.kepu.service.NoticeService;
import com.kepu.service.UserService;
import com.kepu.util.ExceptionUtil;
import com.kepu.util.StringUtil;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	@Autowired
	private UserService userService;

	/**
	 * 获取公告轮播图 区分不同乡镇
	 * 
	 * @return
	 */
	@RequestMapping(value = "getCarousel/{villageId}")
	public @ResponseBody Object getCarousel(@PathVariable Integer villageId) {
		try {
			// 根据类型获取轮播
			return noticeService.getCarousel(5, villageId);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e), "");
		}
	}

	/**
	 * 获取公告列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "getNews/{villageId}/{page}")
	public @ResponseBody Object getNews(@PathVariable Integer villageId, @PathVariable Integer page) {
		try {
			return noticeService.getNews(villageId, page, 10);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e), "");
		}
	}

	/**
	 * 获取公告详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "getNewsDetail/{newsId}")
	public @ResponseBody Object getNewsDetail(@PathVariable Integer newsId, HttpServletRequest request) {
		try {
			String token = request.getHeader("baseParams") == null ? "" : request.getHeader("baseParams");
			// 缓存中取用户信息
			StUser stUser2 = userService.getUserByToken(token);
			/*
			 * if(stUser2==null){ return KePuResult.build(ResultConstant.code_yewu,
			 * "用户id错误", ""); }
			 */
			int userId = stUser2 == null ? -1 : stUser2.getUserid();
			String appVersion = (String) request.getAttribute("appVersion");
			return noticeService.getNewsDetail(userId, newsId, appVersion);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e), "");
		}
	}

	/**
	 * 获取公告评论
	 * 
	 * @return
	 */
	@RequestMapping(value = "getNewsComment/{newsId}/{page}")
	public @ResponseBody Object getNewsComment(@PathVariable Integer newsId, @PathVariable Integer page,
			HttpServletRequest request) {
		try {
			String token = request.getHeader("baseParams") == null ? "" : request.getHeader("baseParams");
			// 缓存中取用户信息
			StUser stUser2 = userService.getUserByToken(token);
			/*
			 * if(stUser2==null){ return KePuResult.build(ResultConstant.code_yewu,
			 * "用户id错误", ""); }
			 */
			int userId = stUser2 == null ? -1 : stUser2.getUserid();
			return noticeService.getNewsComment(newsId, userId, page, 10);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e), "");
		}
	}

	/**
	 * 获取评论回复列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "getReply/{commentId}/{page}")
	public @ResponseBody Object getNewsComment(@PathVariable Long commentId, @PathVariable Integer page,
			HttpServletRequest request) {
		try {
			String token = request.getHeader("baseParams") == null ? "" : request.getHeader("baseParams");
			// 缓存中取用户信息
			StUser stUser2 = userService.getUserByToken(token);
			if (stUser2 == null) {
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			int userId = stUser2 == null ? -1 : stUser2.getUserid();
			return noticeService.getCommentReply(commentId, userId, page, 10);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e), "");
		}
	}

	/**
	 * 发表公告评论
	 * 
	 * @return
	 */
	@RequestMapping(value = "sentComment")
	public @ResponseBody Object sentComment(@RequestBody Map<String, String> map, HttpServletRequest request) {
		try {
			String token = request.getHeader("baseParams") == null ? "" : request.getHeader("baseParams");
			StUser user = userService.getUserByToken(token);
			StringBuffer sb = new StringBuffer();
			if (user == null) {
				return KePuResult.ok(ResultConstant.code_yewu, "用户id错误", "");
			}
			String comment = map.get("comment");
			String newsId = map.get("newsId");
			if (map.containsKey("comment"))
				comment = map.get("comment");
			else {
				sb.append("comment").append(",");
			}
			if (map.containsKey("newsId"))
				newsId = map.get("newsId");
			else {
				sb.append("newsId").append(",");
			}
			if (sb.length() != 0) {
				return KePuResult.build(ResultConstant.code_param, "以下参数不能为空" + sb.toString(), "");
			}
			return userService.noticeSentComment(user, Integer.valueOf(newsId), comment);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e), "");
		}
	}

	/**
	 * 回复评论
	 * 
	 * @return
	 */
	@RequestMapping(value = "replyComment")
	public @ResponseBody Object replyComment(@RequestBody Map<String, String> map, HttpServletRequest request) {
		try {
			String token = request.getHeader("baseParams") == null ? "" : request.getHeader("baseParams");
			StUser user = userService.getUserByToken(token);
			StringBuffer sb = new StringBuffer();
			if (user == null) {
				return KePuResult.ok(ResultConstant.code_yewu, "用户id错误", "");
			}
			String comment = map.get("comment");
			String commentId = map.get("commentId");
			if (map.containsKey("comment"))
				comment = map.get("comment");
			else {
				sb.append("comment").append(",");
			}
			if (map.containsKey("commentId"))
				commentId = map.get("commentId");
			else {
				sb.append("commentId").append(",");
			}
			if (sb.length() != 0) {
				return KePuResult.build(ResultConstant.code_param, "以下参数不能为空" + sb.toString(), "");
			}
			return userService.noticeReplyComment(user, Long.valueOf(commentId), comment);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e), "");
		}
	}

	/**
	 * 收藏公告
	 * 
	 * @return
	 */
	@RequestMapping(value = "likeNews")
	public @ResponseBody Object likeNews(@RequestBody Map<String, String> map, HttpServletRequest request) {
		try {
			String token = request.getHeader("baseParams") == null ? "" : request.getHeader("baseParams");
			StUser user = userService.getUserByToken(token);
			StringBuffer sb = new StringBuffer();
			if (user == null) {
				return KePuResult.ok(ResultConstant.code_yewu, "用户ID错误", "");
			}
			String newsId = map.get("newsId");
			if (map.containsKey("newsId"))
				newsId = map.get("newsId");
			else {
				sb.append("newsId").append(",");
			}
			if (sb.length() != 0) {
				return KePuResult.build(ResultConstant.code_param, "以下参数不能为空" + sb.toString(), "");
			}
			return noticeService.likeNews(Integer.valueOf(newsId), user.getUserid());
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e), "");
		}
	}

	/**
	 * 获取我的收藏
	 * 
	 * @return
	 */
	@RequestMapping(value = "getMyLikeNews/{page}")
	public @ResponseBody Object getMyLikeNews(@PathVariable Integer page, HttpServletRequest request) {
		try {
			String token = request.getHeader("baseParams") == null ? "" : request.getHeader("baseParams");
			StUser user = userService.getUserByToken(token);
			if (user == null) {
				return KePuResult.ok(ResultConstant.code_yewu, "用户ID错误", "");
			}

			return noticeService.getMyLikeNews(user.getUserid(), page, 10);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e), "");
		}
	}

	/**
	 * 删除收藏的公告
	 * 
	 * @return
	 */
	@RequestMapping(value = "delete/likeNews")
	public @ResponseBody Object deletelikeNews(@RequestBody Map<String, String> map, HttpServletRequest request) {
		try {
			String token = request.getHeader("baseParams") == null ? "" : request.getHeader("baseParams");
			StUser user = userService.getUserByToken(token);
			StringBuffer sb = new StringBuffer();
			if (user == null) {
				return KePuResult.ok(ResultConstant.code_yewu, "用户ID错误", "");
			}
			String newsId = map.get("newsId");
			if (map.containsKey("newsId"))
				newsId = map.get("newsId");
			else {
				sb.append("newsId").append(",");
			}
			if (sb.length() != 0) {
				return KePuResult.build(ResultConstant.code_param, "以下参数不能为空" + sb.toString(), "");
			}
			String[] newsIds = newsId.split(",");
			return noticeService.deletelikeNews(newsIds, user.getUserid());
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e), "");
		}
	}

	/**
	 * 举报公告评论
	 * 
	 * @return
	 */
	@RequestMapping(value = "report/{commentId}")
	public @ResponseBody Object reportNewsComment(@PathVariable Long commentId, HttpServletRequest request) {
		try {
			String token = request.getHeader("baseParams") == null ? "" : request.getHeader("baseParams");
			StUser user = userService.getUserByToken(token);
			StringBuffer sb = new StringBuffer();
			if (user == null) {
				return KePuResult.ok(ResultConstant.code_yewu, "用户id错误", "");
			}
			return noticeService.reportNewsComment(user.getUserid(), commentId);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e), "");
		}
	}

	/**
	 * 评论/回复点赞
	 * 
	 * @return
	 */
	@RequestMapping(value = "praise/{type}/{typeId}")
	public @ResponseBody Object praise(@PathVariable Integer type, @PathVariable Long typeId,
			HttpServletRequest request) {
		try {
			String token = request.getHeader("baseParams") == null ? "" : request.getHeader("baseParams");
			StUser user = userService.getUserByToken(token);
			if (user == null) {
				return KePuResult.ok(ResultConstant.code_yewu, "用户ID错误", "");
			}
			return noticeService.praise(type, typeId, user.getUserid());
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e), "");
		}
	}

	/**
	 * 公告点赞/不喜欢
	 * 
	 * @return
	 */
	@RequestMapping(value = "dp/{newsId}/{type}/{operate}")
	public @ResponseBody Object dp(@PathVariable Integer newsId, @PathVariable Integer type,
			@PathVariable Integer operate, HttpServletRequest request) {
		try {
			// type=1 点赞 type=2 不喜欢
			// operate=1 点击 operate=0 取消
			String token = request.getHeader("baseParams") == null ? "" : request.getHeader("baseParams");
			StUser user = userService.getUserByToken(token);
			if (user == null) {
				return KePuResult.ok(ResultConstant.code_yewu, "用户ID错误", "");
			}
			return noticeService.dpNews(newsId, type, user.getUserid(), operate);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e), "");
		}
	}

	/**
	 * 公告搜索接口
	 * 
	 * @return
	 */
	@RequestMapping(value = "search/{page}")
	public @ResponseBody Object search(@PathVariable Integer page, HttpServletRequest request) {
		try {
			String query = request.getParameter("q");
			if (StringUtil.isEmpty(query))
				return KePuResult.build(ResultConstant.code_param, "搜索词不能为空", "");
			if (page == 1)
				noticeService.addHotSearch(query);
			return noticeService.searchNews(query, page, 10);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e), "");
		}
	}

	/**
	 * 获取公告热搜词汇
	 * 
	 * @return
	 */
	@RequestMapping(value = "getHotSearch")
	public @ResponseBody Object getHotSearch(HttpServletRequest request) {
		try {
			return noticeService.getHotSearch();
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e), "");
		}
	}
}
