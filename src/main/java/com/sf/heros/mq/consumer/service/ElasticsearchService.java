/**
 *@Pr锛歨eros
 *@Date: 2014-5-4 涓婂崍9:21:27
 *@Author: seaphy
 *@Copyright: 漏 2012 sf-express.com Inc. All rights reserved
 *娉ㄦ剰锛氭湰鍐呭浠呴檺浜庨『涓伴�熻繍鍏徃鍐呴儴浼犻槄锛岀姝㈠娉勪互鍙婄敤浜庡叾浠栫殑鍟嗕笟鐩殑
 */
package com.sf.heros.mq.consumer.service;

import com.sf.heros.mq.consumer.vo.*;
import org.apache.log4j.Logger;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.cluster.health.ClusterHealthStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;

import java.util.ArrayList;
import java.util.List;

//import org.elasticsearch.action.admin.cluster.health.ClusterHealthStatus;

/**
 * @author seaphy
 * @date 2014-5-4
 */
public class ElasticsearchService {

    private static final Logger logger = Logger.getLogger(ElasticsearchService.class);

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private Client esClient;

    public void init() {
//    	if (!elasticsearchTemplate.indexExists(APP.ESProp.INDEX_NAME)) {
//			elasticsearchTemplate.createIndex(APP.ESProp.INDEX_NAME);
//		}
//    	elasticsearchTemplate.putMapping(TaskInfo.class);
//    	elasticsearchTemplate.putMapping(NewsInfo.class);
        System.out.println("hehe");
    }

    public boolean update(List<TaskInfo> taskInfoList) {
        List<IndexQuery> queries = new ArrayList<IndexQuery>();
        for (TaskInfo taskInfo : taskInfoList) {
            IndexQuery indexQuery = new IndexQueryBuilder().withId(taskInfo.getTaskId()).withObject(taskInfo).build();
            queries.add(indexQuery);
        }
        elasticsearchTemplate.bulkIndex(queries);
        return true;
    }

    public boolean insertOrUpdateTaskInfo(List<TaskInfo> taskInfoList) {
        List<IndexQuery> queries = new ArrayList<IndexQuery>();
        for (TaskInfo taskInfo : taskInfoList) {
            IndexQuery indexQuery = new IndexQueryBuilder().withId(taskInfo.getTaskId()).withObject(taskInfo).build();
            queries.add(indexQuery);
        }
        elasticsearchTemplate.bulkIndex(queries);
        return true;
    }
    public boolean insertOrUpdateDecPrice(List<DecPriceVo> decPriceVoList) {
        List<IndexQuery> queries = new ArrayList<IndexQuery>();
        for (DecPriceVo decPriceVo : decPriceVoList) {
            IndexQuery indexQuery = new IndexQueryBuilder().withId(decPriceVo.getPid()).withObject(decPriceVo).build();
            queries.add(indexQuery);
        }
        elasticsearchTemplate.bulkIndex(queries);
        return true;
    }
    public boolean insertOrUpdateNewsInfo(List<NewsInfo> newsInfos) {
        List<IndexQuery> queries = new ArrayList<IndexQuery>();
        for (NewsInfo newsInfo : newsInfos) {
            IndexQuery indexQuery = new IndexQueryBuilder().withId(newsInfo.getNewsId()).withObject(newsInfo).build();
            queries.add(indexQuery);
        }
        elasticsearchTemplate.bulkIndex(queries);
        return true;
    }

    public boolean insertOrUpdateNewsInfo(NewsInfo newsInfo) {
        try {
            IndexQuery indexQuery = new IndexQueryBuilder().withId(newsInfo.getNewsId()).withObject(newsInfo).build();
            elasticsearchTemplate.index(indexQuery);
            return true;
        } catch (Exception e) {
            logger.error("insert or update news info error.", e);
            return false;
        }
    }

    public boolean insertOrUpdateTaskInfo(TaskInfo taskInfo) {
        try {
            IndexQuery indexQuery = new IndexQueryBuilder().withId(taskInfo.getTaskId()).withObject(taskInfo).build();
            elasticsearchTemplate.index(indexQuery);
            return true;
        } catch (Exception e) {
            logger.error("insert or update task info error.", e);
            return false;
        }
    }

    public boolean insertOrUpdateUserInfo(UserInfo userInfo) {
        try {
            IndexQuery indexQuery = new IndexQueryBuilder().withId(userInfo.getUserId()).withObject(userInfo).build();
            elasticsearchTemplate.index(indexQuery);
            return true;
        } catch (Exception e) {
            logger.error("insert or update user info error.", e);
            return false;
        }
    }

    public <T> boolean deleteById(String id, Class<T> clzz) {
        try {
            elasticsearchTemplate.delete(clzz, id);
            return true;
        } catch (Exception e) {
            logger.error("delete " + clzz + " by id " + id + " error.", e);
            return false;
        }
    }

    /**
     * 检查健康状态
    * @author 高国藩
    * @date 2015年6月15日 下午6:59:47
    * @return
     */
    public boolean ping() {
    	try {
    		ActionFuture<ClusterHealthResponse> health = esClient.admin().cluster().health(new ClusterHealthRequest());
    		ClusterHealthStatus status = health.actionGet().getStatus();
    		if (status.value() == ClusterHealthStatus.RED.value()) {
				throw new RuntimeException("elasticsearch cluster health status is red.");
			}
    		return true;
		} catch (Exception e) {
			logger.error("ping elasticsearch error.", e);
			return false;
		}
    }

	public boolean insertOrUpdateBrandCaseInfo(BrandCaseInfo brandCaseInfo) {
		try {
			IndexQuery indexQuery = new IndexQueryBuilder()
					.withId(brandCaseInfo.getId()).withObject(brandCaseInfo).build();
			elasticsearchTemplate.index(indexQuery);
			return true;
		} catch (Exception e) {
			logger.error("insert or update brandcase info error.", e);
			return false;
		}
	}
//    public void search(TaskInfo taskInfo) {
//        QueryStringQueryBuilder queryString = QueryBuilders.queryStringQuery("\""
//                + "高国藩" + "\"");
//        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(queryString(documentId).field("id"))
//                .build();
//        Page<TaskInfo> sampleEntities = elasticsearchTemplate.queryForPage(searchQuery, TaskInfo.class);
//
//    }
}
