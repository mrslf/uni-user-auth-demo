package com.coocap.uni.sso.client.common;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;

/**
 * HttpClient工具类
 */
public class HttpUtil {
    public static final String JSON_CONTENT_TYPE = "application/json;charset=utf-8";
    public static final String POST_METHOD = "POST";
    public static final String GET_METHOD = "GET";
    private static PoolingHttpClientConnectionManager cm;
    private static String EMPTY_STR = "";
    private static String UTF_8 = "UTF-8";

    private static void init() {
        if (cm == null) {
            cm = new PoolingHttpClientConnectionManager();
            cm.setMaxTotal(50);//整个连接池最大连接数  
            cm.setDefaultMaxPerRoute(5);//每路由最大连接数，默认值是2  
        }
    }

    /**
     * 通过连接池获取HttpClient
     *
     * @return
     */
    private static CloseableHttpClient getHttpClient() {
        init();
        return HttpClients.custom().setConnectionManager(cm).build();
    }

    /**
     * HttpGet请求
     *
     * @param url 地址
     * @return 请求结果
     */
    public static String httpGetRequest(String url) {
        HttpGet httpGet = new HttpGet(url);
        return getResult(httpGet);
    }

    /**
     * HttpGet请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return 请求结果
     * @throws URISyntaxException
     */
    public static String httpGetRequest(String url, Map<String, String> params) throws URISyntaxException {
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);

        HttpGet httpGet = new HttpGet(ub.build());
        return getResult(httpGet);
    }

    /**
     * HttpGet请求
     *
     * @param url     请求地址
     * @param headers 请求头
     * @param params  请求参数
     * @return 返回结果
     * @throws URISyntaxException
     */
    public static String httpGetRequest(String url, Map<String, String> headers,
                                        Map<String, String> params) throws URISyntaxException {
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);

        HttpGet httpGet = new HttpGet(ub.build());
        for (Map.Entry<String, String> param : headers.entrySet()) {
            httpGet.addHeader(param.getKey(), param.getValue());
        }
        return getResult(httpGet);
    }

    /**
     * HttpPost请求
     *
     * @param url
     * @return
     */
    public static String httpPostRequest(String url) {
        HttpPost httpPost = new HttpPost(url);
        return getResult(httpPost);
    }

    /**
     * HttpPOST请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return 请求结果
     * @throws UnsupportedEncodingException
     */
    public static String httpPostRequest(String url, Map<String, String> params) throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
        return getResult(httpPost);
    }

    /**
     * HttpPost请求
     *
     * @param url     地址
     * @param headers 头部信息
     * @param params  参数信息
     * @return 请求结果
     * @throws UnsupportedEncodingException
     */
    public static String httpPostRequest(String url, Map<String, String> headers,
                                         Map<String, String> params) throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);

        for (Map.Entry<String, String> param : headers.entrySet()) {
            httpPost.addHeader(param.getKey(), param.getValue());
        }

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));

        return getResult(httpPost);
    }


    /**
     * 参数转化成为键值对方式
     *
     * @param params 参数
     * @return 键值对列表
     */
    private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, String> params) {
        ArrayList<NameValuePair> pairs = new ArrayList<>();
        for (Map.Entry<String, String> param : params.entrySet()) {
            pairs.add(new BasicNameValuePair(param.getKey(), param.getValue()));
        }

        return pairs;
    }


    /**
     * 处理Http请求
     *
     * @param request 请求
     * @return 请求结果
     */
    private static String getResult(HttpRequestBase request) {
        //CloseableHttpClient httpClient = HttpClients.createDefault();  
        CloseableHttpClient httpClient = getHttpClient();
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            //response.getStatusLine().getStatusCode();  
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                //long len = entity.getContentLength();// -1 表示长度未知  
                String result = EntityUtils.toString(entity);
                response.close();
                //httpClient.close();  
                return result;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }

        return EMPTY_STR;
    }

    public static String httpPostRequestJson(String url,String params){
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-type",JSON_CONTENT_TYPE);
        httpPost.setEntity(new StringEntity(params, UTF_8));
        return getResult(httpPost);

    }
}