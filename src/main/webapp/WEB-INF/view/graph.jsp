<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
        <script type="text/javascript" src="js/vivagraph.js"></script>
        <style type="text/css">
            svg,
            #graphContainer {
                height: 800px;
                width: 100%;
                margin: 0;
            }

            #graphContainer .label {
                position: absolute;
                top: 20px;
                color: white;
                font-size: 16px;
                left: 20px;
                font-family: Verdana;
            }
        </style>
        <title>Akademik Arama Projesi</title>
    </head>
    <body>
    <div class="row mt-5">
        <div class="col-md-6 m-auto">
            <div class="card card-body">
                <img src="kouLogo.png" class="rounded mx-auto d-block" alt="..." height="300px">     
            </div>

            <div class="card card-body" id="graphContainer">
                <div class="btn-group">
                    <button type="button" class="btn btn-primary zoom out" title="Kucult"> <i class="fa fa-search-minus">Küçült</i>
                    </button>
                    <button type="button" class="btn btn-warning reset" title="Yenile"> <i class="fa fa-refresh">Sıfırla</i>
                    </button>
                    <button type="button" class="btn btn-primary zoom in" title="Buyult"> <i class="fa fa-search-plus">Büyült</i>
                    </button>
                </div>
                <h4>Araştırmacının Çizge Yapısı</h4>
            </div>

        </div>
    </div>


    <script>
        $(document).ready(function () {
            yazarlariGetir();
        });
        CollaboratedAuthor = function (graph, nodeID) {
            graph.forEachNode(function (node) {
                if (node.id != nodeID) {
                    graph.removeNode(node.id);
                }
            });
        }
        function yazarlariGetir() {

            var bpmnJsonString = '${arastirmaci}';
            var jsonOA = '${ortakCalistigiArastirmacilar}';
            var jsonY = '${arastirmacininYayinlari}';

            var jsonArastirmaci = JSON.parse(bpmnJsonString);
            var jsonOrtak = JSON.parse(jsonOA)
            var jsonYayinlar = JSON.parse(jsonY);

            console.log(jsonArastirmaci);
            console.log(jsonOrtak);
            console.log(jsonYayinlar);

            var graph = Viva.Graph.graph();
            graph.Name = "Birlikte çalıştığı yazarlar";
            var layout = Viva.Graph.Layout.forceDirected(graph, {
                springLength: 200,
                springCoeff: 0.00001,
                dragCoeff: 0.009,
                gravity: -1.4,
                theta: 0.09
            });
            renderLinks = true;
            graph.addNode(jsonArastirmaci.arastirmaciId, {url: '/profile.png', name: jsonArastirmaci.arastirmaciAdi + " " + jsonArastirmaci.arastirmaciSoyadi});
            for (var i = 0; i < jsonOrtak.length; i++) {
                graph.addNode(jsonOrtak[i].arastirmaciId, {url: '/profile2.png', name: jsonOrtak[i].arastirmaciAdi + " " + jsonOrtak[i].arastirmaciSoyadi});
                graph.addLink(jsonArastirmaci.arastirmaciId, jsonOrtak[i].arastirmaciId);
            }
            for (var i = 0; i < jsonYayinlar.length; i++) {
                graph.addNode(jsonYayinlar[i].yayinId, {url: '/article.png', name: jsonYayinlar[i].yayinAdi + " , " + jsonYayinlar[i].yayinYili});
                graph.addNode(jsonYayinlar[i].yayinIcerik.turId, {url: 'type.png', name: jsonYayinlar[i].yayinIcerik.yayinYeri + " , " + jsonYayinlar[i].yayinIcerik.yayinTuru});
                graph.addLink(jsonYayinlar[i].yayinId, jsonYayinlar[i].yayinIcerik.turId);
                for (var j = 0; j < jsonYayinlar[i].yayininYazarlari.length; j++) {
                    graph.addLink(jsonYayinlar[i].yayinId, jsonYayinlar[i].yayininYazarlari[j].arastirmaciId);
                }
            }


            var graphics = Viva.Graph.View.svgGraphics();
            nodeSize = 60;
            nodeCircle = "circleClass";
            highlightRelatedNodes = function (nodeId, isOn) {
                graph.forEachLinkedNode(nodeId, function (node, link) {
                    var linkUI = graphics.getLinkUI(link.id);
                    if (linkUI) {
                        linkUI.attr('stroke-width', isOn ? '3' : '9');
                        linkUI.source = 'google.com';
                        linkUI.target = 'google.com'

                    }
                });
            };

            graphics.node(function (node) {
                var ui = Viva.Graph.svg('g'),
                        svgText = Viva.Graph.svg('text').attr('y', '-10px').text(node.data.name).attr('font-size', '15px').attr('font-family', 'Times New Roman').attr('font-weight', 'Bold'),
                        rect = Viva.Graph.svg('rect')
                        .attr('stroke-width', 8)
                        .attr('stroke', 'rgb(153, 200, 224)')
                        .attr('width', nodeSize)
                        .attr('class', nodeCircle)
                        .attr('height', nodeSize),
                        circle = Viva.Graph.svg('circle')
                        .attr('cx', nodeSize / 2)
                        .attr('class', nodeCircle)
                        .attr('cy', nodeSize / 2)
                        .attr('r', nodeSize - 20)
                        .attr('fill', ' url(#' + node.id + ')')
                        .attr('filter', ' url(#f_' + node.id + ')'),
                        filter = Viva.Graph.svg('filter')
                        .attr('id', 'f_' + node.id)
                        .attr('x', '0')
                        .attr('y', '0')
                        .attr('width', '200%')
                        .attr('height', '200%'),
                        feOffset = Viva.Graph.svg('feOffset')
                        .attr('result', 'offOut')
                        .attr('in', 'SourceAlpha')
                        .attr('dx', '5')
                        .attr('dy', '1'),
                        feGaussianBlur = Viva.Graph.svg('feGaussianBlur')
                        .attr('result', 'blurOut')
                        .attr('in', 'offOut')
                        .attr('stdDeviation', '3'),
                        feBlend = Viva.Graph.svg('feBlend')
                        .attr('mode', 'normal')
                        .attr('in', 'SourceGraphic')
                        .attr('in2', 'blurOut'),
                        defs = Viva.Graph.svg('defs'),
                        pattern = Viva.Graph.svg('pattern')
                        .attr('id', node.id)
                        .attr('x', -8)
                        .attr('y', -11)
                        .attr('patternUnits', 'userSpaceOnUse')
                        .attr('height', 94)
                        .attr('width', 76),
                        img = Viva.Graph.svg('image')
                        .attr('width', 76)
                        .attr('height', 94)
                        .attr('x', 0)
                        .attr('y', 0)
                        .link(node.data.url);
                filter.append(feOffset);
                filter.append(feGaussianBlur);
                filter.append(feBlend);
                pattern.append(img);
                defs.append(filter);
                defs.append(pattern);
                ui.append(defs);
                ui.append(circle);
                ui.append(svgText);
                $(ui).hover(function () {
                    console.log("hovering in.", node.id);
                    highlightRelatedNodes(node.id, true);
                }, function () {
                    console.log("hovering out.", node.id);
                    highlightRelatedNodes(node.id, false);
                }).dblclick(function (e) {
                    renderer.rerender();
                    let urlString = node.data.name.replaceAll(" ", "%20");
                    window.open('http://localhost:8080/graph?graphSearchText=' + urlString, '_blank').focus();
                }).click(function (e) {
                    console.log("click." + node.id + " " + node.data.name);
                    showQuickPreview(node);
                })

                return ui;
            })

                    .placeNode(function (nodeUI, pos) {
                        nodeUI.attr('transform',
                                'translate(' +
                                (pos.x - nodeSize) + ',' + (pos.y - nodeSize) +
                                ')');
                    });

            var renderer = Viva.Graph.View.renderer(graph,
                    {
                        container: document.getElementById('graphContainer'),
                        graphics: graphics,
                        layout: layout,
                        renderLinks: renderLinks
                    });
            renderer.run();
            var graphics = renderer.getGraphics();
            $('.zoom').click(function (e) {
                e.preventDefault();
                if ($(this).hasClass('in')) {
                    renderer.zoomIn();
                } else {
                    renderer.zoomOut();
                }
            });
            $('.reset').click(function () {
                renderer.reset()
            });
        }
    </script>



    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
    crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
            integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
    crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
            integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
    crossorigin="anonymous"></script>
</body>
</html>
