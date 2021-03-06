package com.kepu.apppay.alipay.util;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088521250928891";//
	
	// 卖家支付宝用户号，以2088开头由16位纯数字组成的字符串
	public static String seller_id = partner;
	
	//卖家支付宝账号,一般情况下收款账号就是签约账号
	public static String seller_email = "2147620158@qq.com";
	
	// MD5密钥，安全检验码，由数字和字母组成的32位字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
    public static String key = "qmn7kbw89077eh3drfkujhxt7pvgn4v2";
			
	// 签名方式
	public static String sign_type = "MD5";
	
	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public static String log_path = "D:\\";
		
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
		
	// 支付类型 ，无需修改
//	public static String payment_type = "1";
		
	// 调用的接口名，无需修改
//	public static String  service = "http://wap.dadaofangzhiwang.com/ialipayController/callbacks.do";
	public static String  service = "http://kp.appwzd.cn/kepu/order/async";
		
	//入口
	public static final String GATEWAY = "https://openapi.alipay.com/gateway.do";
	//appid
	public static final String APP_ID = "2018022702283534";
	//商家私钥
	public static final String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCcKMBKtSf1kmbOrMD28dBkUQPIvAXlDm3YeTHr4A3O9hw044kq9NBbDEwfNlo4BKucSNGzX693l/dQBF0o5TKeijD8jnEjysrx8phdOZaIf9qUupyq/i5JulwAj7xOaOzxnpbSGNcFsfiWadWHZCbmOaqooL1k4pSsjGLh8zMx4Q9JvHJWeT0jIQo3txKSfmjNPUT1pwK2ZikblIpo/drmGK7G9b0yymXQGPUfhQlSNs5MhwI65FsclRw9qWRk/2n1Stwc9jGrRnq3DxAdg1EUxd6QjILm4rkJocRvrabAZ5+SXiMXSDVc8/95YIF9mr0RtYdOkDoQn+eNhLeSeCclAgMBAAECggEARZMGcYKa5hevRF/FrYB5d2h9Bh93hOFT4YHF0zDFFjOix5Gk2IWwmxA0jid6NMEn8H6lJ4T3OZJ0dqsrEoBjeC1bu/p+wBKnLvsGFWsF/ZTxPxiRmsGav/CO8xUXMdwfzVr6GWtFydzMQ7+tih/+EM8akPzkxSEKWoJFwCPJ6GuF+t5XrjK+zusCE273PFsP5dA6Zp/IbH+Kw0ekQ42eVTUs1LRlyyGIK0+Of86sydhXJk63RYN9JIzoLRMAVSwCmRId972N3tPwxwQDMnFSwGr9TmWulwJ3TJK6H7NnW9qlbusZb+mnx1kh6wueTxJuW3xfDzhfoQyiISeYl34zIQKBgQD5x2C8981QgPfN5JWoT9I2UekLsm1uB7DBg2SxzVtLKy/+UIn2X+WrFGLU/EZz/7svSdQhuxsPtK7qTyDRTuYFkDilq97/U2s/on10lLi90CNR0BgV/nLr8uL4ZYdNWCZ60Vx23Vycz9bHcUAOln9gqrP0draXskQs3Or8krOzSQKBgQCgDHE8iE3pRQs7y3jJDQ6nNBwwb2KD9eRBZ7ePoo20ah8XQL6tUtDr0sxnvO+VZYQOMX3dMRwLZQFpWc9k7KBTKEfDhQXtoSmWtYXiNpwUdVcbyfVyaqZm5ToGnG38wnZit5XtQQ5hBLGdK9Kv/PUj1e8YiotBOwg1jvHc4mg4/QKBgB4iuv4xm5jh5oP110SWVNNu8IITxfYngWDnnGeDUkdwb3rsdqi+CzSn4gC2az2llgzviGOFco9jilMEtv3p2gDD1MHQSbhedSsZTK+bHerwyMIfLDjDGVR0WoQDo95mC0Z4KgLBVGIrBUvM0yCvcbDVB58wD3mf7W/IO6W7gWjZAoGAcua73ONWW+QPnowDWQOgw3Vrz3IH3gkIJ9SYOexPo3iUfZMj0TclekhKKye/MRhCaBYr8EKziZKoIsQxWpluuISwkLPNf742J1aGmdLu1tkvAmpJZi0e7bwfnmNrqwzJBTIb1srf0dMW2Gl/xozqSSHrl0jPlLMWv6yyAwwgGtkCgYBpM5LpF4qAPgyreyw6XzHGjWVvlaP2J5anegXA6OPP6Kbdk0NvhUGXRO5NUXtjGQrTqn4SlJbo0bwve20uy5UwVf0ybn86KiGNSlidsKIh6u4QevsHQrU+vi0Iz9kp3dYCOTje2o/4wioH6aEurJue9z26fLigeip2+I386BsyCg==";
	//格式
	public static final String FORMAT = "json";
	//编码
	public static final String CHARSET = "utf-8";
	//阿里公钥
	public static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAghqGh87fTDyJF805nZwyH6F9LSIV1A4Jc2mvTXumk8E2OjTJ/lcht3FYKGJTN8SFXR7BSXN0Pn7nfJg72kEcp2x7t5Biycge3/0NY25f4orTZwTKJaGuW/dAKY0orw9YZ7Hihz7w7F+74qESsSPM7kMjvxqJztN/Mynk1FvyVMmJsJeJxdeSEDkPw8f5tytJf098JukEhOpGulDkA1bfq50V0W9QKpCDUd3NsW/D83IlQuuIVftlgOnsLzZbs3zpFszoqqMSSGPJPGeqrl5i2RkYAXvU4871NRqJhv2fY/67+AunvheLJYqUfeBdZByc/NDqmXLDKzEAg1GBTYSAewIDAQAB";
	//加密类型
	public static final String SIGN_TYPE = "RSA2";
	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

}
