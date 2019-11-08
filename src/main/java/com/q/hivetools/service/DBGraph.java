package com.q.hivetools.service;

import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.io.graphml.GraphMLIo;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.apache.tinkerpop.gremlin.util.Gremlin;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class DBGraph {
    public static void main(String[] args) {
        System.out.println("Gremlin version is " + Gremlin.version());
        // Create a new TinkerGraph instance and try to load the air-routes data
        TinkerGraph tg = TinkerGraph.open() ;
        try{
            tg.io(GraphMLIo.build()).readGraph("tables.sample");
        }
        catch( IOException e ){
            System.out.println("TinkerGraphTest - Air routes GraphML file not found");
            System.exit(0);
        }
        // Create our graph traversal source
        GraphTraversalSource g = tg.traversal();

        List<Object> tables = g.V().values("name").toList();
        AtomicInteger visited = new AtomicInteger();
        while (tables.size()> visited.get()) {
            tables.stream().forEach(t -> {
                long outC = g.V().has("name", t).has("visited", "n").out().has("visited", "n").count().next();
                if (outC == 0) {
                    StringBuffer sb = new StringBuffer();
                    sb.append(String.format("select * from %s where 1=1 ", t));
                    List<Path> fks = g.V().has("name", t).outE().inV().path().by("name").by("rr").toList();
                    fks.forEach((k) -> {
                        String t1 = k.get(0).toString();
                        String t2 = k.get(2).toString();
                        String[] fkCols = k.get(1).toString().split(",");
                        sb.append(String.format(" and %s = #{item.%s} ", fkCols[0], fkCols[1]));
                    });
                    System.out.println(sb.toString());
                    visited.addAndGet(1);
                    Vertex vertex = g.V().has("name", t).next();
                    vertex.property("visited", "y");
                }
            });
        }






    }
}
