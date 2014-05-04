package edu.sjsu.cmpe283.lifechoices.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wolfram.alpha.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: maksim
 * Date: 5/3/14 - 6:24 PM
 */
public class WolframAlphaService {

    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    String WOLFRAMALPHA_APP_ID = "YQJ6KK-UY257H9AHR";


    public String formattedJson(String queryString){

        List<Map> pods = new ArrayList<Map>();


        WAEngine engine = new WAEngine();

        // These properties will be set in all the WAQuery objects created from this WAEngine.
        engine.setAppID(WOLFRAMALPHA_APP_ID);
        engine.addFormat("image");



        // Create the query.
        WAQuery query = engine.createQuery();

        // Set properties of the query.
        query.setInput(queryString);


        try {
            // For educational purposes, print out the URL we are about to send:
            System.out.println("Query URL:");
            System.out.println(engine.toURL(query));
            System.out.println("");

            // This sends the URL to the Wolfram|Alpha server, gets the XML result
            // and parses it into an object hierarchy held by the WAQueryResult object.
            WAQueryResult queryResult = engine.performQuery(query);

            if (queryResult.isError()) {
                System.out.println("Query error");
                System.out.println("  error code: " + queryResult.getErrorCode());
                System.out.println("  error message: " + queryResult.getErrorMessage());
                return "{\"status\":\"error: " + queryResult.getErrorMessage() + "\"";

            } else if (!queryResult.isSuccess()) {
                System.out.println("Query was not understood; no results available.");
                return "{\"status\":\"error: Query was not understood; no results available.\"";
            } else {
                // Got a result.

                System.out.println("Successful query. Pods follow:\n");
                for (WAPod pod : queryResult.getPods()) {
                    Map<String, Object> podMap = new HashMap<String, Object>();
                    podMap.put("title", pod.getTitle());
                    podMap.put("number-of-images", pod.getNumSubpods());
                    if (!pod.isError()) {
                        List<WAImage> podImages = new ArrayList<WAImage>();
                        System.out.println(pod.getTitle());
                        System.out.println("------------");
                        for (WASubpod subpod : pod.getSubpods()) {
                            for (Object element : subpod.getContents()) {
                                if (element instanceof WAPlainText) {
                                    System.out.println(((WAPlainText) element).getText());
                                    System.out.println("");
                                } else if (element instanceof WAImage){
                                    WAImage image = ((WAImage) element);
                                    System.out.println(image.getURL());
                                    podImages.add(image);

                                }
                            }
                        }
                        System.out.println("");

                        podMap.put("images", podImages);
                    }

                    pods.add(podMap);
                }
                // We ignored many other types of Wolfram|Alpha output, such as warnings, assumptions, etc.
                // These can be obtained by methods of WAQueryResult or objects deeper in the hierarchy.
            }
        } catch (WAException e) {
            e.printStackTrace();
        }


        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("query", queryString);
        resultMap.put("result", pods);

         return gson.toJson(resultMap);

    }


    public String rawJson(String queryString){

        String resultJson = "{}";

        // The WAEngine is a factory for creating WAQuery objects,
        // and it also used to perform those queries. You can set properties of
        // the WAEngine (such as the desired API output format types) that will
        // be inherited by all WAQuery objects created from it. Most applications
        // will only need to crete one WAEngine object, which is used throughout
        // the life of the application.
        WAEngine engine = new WAEngine();

        // These properties will be set in all the WAQuery objects created from this WAEngine.
        engine.setAppID(WOLFRAMALPHA_APP_ID);
        engine.addFormat("image");



        // Create the query.
        WAQuery query = engine.createQuery();

        // Set properties of the query.
        query.setInput(queryString);


        try {
            // For educational purposes, print out the URL we are about to send:
            System.out.println("Query URL:");
            System.out.println(engine.toURL(query));
            System.out.println("");

            // This sends the URL to the Wolfram|Alpha server, gets the XML result
            // and parses it into an object hierarchy held by the WAQueryResult object.
            WAQueryResult queryResult = engine.performQuery(query);
            resultJson = gson.toJson(queryResult);
//
//            if (queryResult.isError()) {
//                System.out.println("Query error");
//                System.out.println("  error code: " + queryResult.getErrorCode());
//                System.out.println("  error message: " + queryResult.getErrorMessage());
//            } else if (!queryResult.isSuccess()) {
//                System.out.println("Query was not understood; no results available.");
//
//            } else {
//                // Got a result.
//
//
//
//                System.out.println("Successful query. Pods follow:\n");
//                assumptions.addAll(Arrays.asList(queryResult.getAssumptions()));
//                for (WAPod pod : queryResult.getPods()) {
//                    if (!pod.isError()) {
//                        System.out.println(pod.getTitle());
//                        System.out.println("------------");
//                        for (WASubpod subpod : pod.getSubpods()) {
//                            for (Object element : subpod.getContents()) {
//                                if (element instanceof WAPlainText) {
//                                    System.out.println(((WAPlainText) element).getText());
//                                    System.out.println("");
//                                } else if (element instanceof WAImage){
//                                    WAImage image = ((WAImage) element);
//                                    System.out.println(image.getURL());
//                                    podImages.add(image);
//                                }
//                            }
//                        }
//                        System.out.println("");
//                    }
//                }
//                // We ignored many other types of Wolfram|Alpha output, such as warnings, assumptions, etc.
//                // These can be obtained by methods of WAQueryResult or objects deeper in the hierarchy.
//            }
        } catch (WAException e) {
            e.printStackTrace();
        }



         return resultJson;

    }


    public String sampleJsonResponse = "" +
            "";


    public String sampleXmlResponse = "<?xml version='1.0' encoding='UTF-8'?>\n" +
            "<queryresult success='true'\n" +
            "    error='false'\n" +
            "    numpods='8'\n" +
            "    datatypes='MathematicalFunctionIdentity'\n" +
            "    timedout=''\n" +
            "    timedoutpods=''\n" +
            "    timing='2.706'\n" +
            "    parsetiming='0.121'\n" +
            "    parsetimedout='false'\n" +
            "    recalculate=''\n" +
            "    id='MSPa18241gi96b3cc7b35b1a00005hbib393587gggdb'\n" +
            "    host='http://www3.wolframalpha.com'\n" +
            "    server='42'\n" +
            "    related='http://www3.wolframalpha.com/api/v2/relatedQueries.jsp?id=MSPa18251gi96b3cc7b35b1a00002d18iaac52ie263i&amp;s=42'\n" +
            "    version='2.6'>\n" +
            " <pod title='Input'\n" +
            "     scanner='Identity'\n" +
            "     id='Input'\n" +
            "     position='100'\n" +
            "     error='false'\n" +
            "     numsubpods='1'>\n" +
            "  <subpod title=''>\n" +
            "   <img src='http://www3.wolframalpha.com/Calculate/MSP/MSP18261gi96b3cc7b35b1a000049559a3cefd2if40?MSPStoreType=image/gif&amp;s=42'\n" +
            "       alt='pi'\n" +
            "       title='pi'\n" +
            "       width='9'\n" +
            "       height='18' />\n" +
            "  </subpod>\n" +
            " </pod>\n" +
            " <pod title='Decimal approximation'\n" +
            "     scanner='Numeric'\n" +
            "     id='DecimalApproximation'\n" +
            "     position='200'\n" +
            "     error='false'\n" +
            "     numsubpods='1'\n" +
            "     primary='true'>\n" +
            "  <subpod title=''>\n" +
            "   <img src='http://www3.wolframalpha.com/Calculate/MSP/MSP18271gi96b3cc7b35b1a0000585a110dbf75ha85?MSPStoreType=image/gif&amp;s=42'\n" +
            "       alt='3.1415926535897932384626433832795028841971693993751058...'\n" +
            "       title='3.1415926535897932384626433832795028841971693993751058...'\n" +
            "       width='443'\n" +
            "       height='20' />\n" +
            "  </subpod>\n" +
            "  <states count='1'>\n" +
            "   <state name='More digits'\n" +
            "       input='DecimalApproximation__More digits' />\n" +
            "  </states>\n" +
            " </pod>\n" +
            " <pod title='Property'\n" +
            "     scanner='Numeric'\n" +
            "     id='Property'\n" +
            "     position='300'\n" +
            "     error='false'\n" +
            "     numsubpods='1'>\n" +
            "  <subpod title=''>\n" +
            "   <img src='http://www3.wolframalpha.com/Calculate/MSP/MSP18281gi96b3cc7b35b1a00001b7fd5d0d2fg8dhf?MSPStoreType=image/gif&amp;s=42'\n" +
            "       alt='pi is a transcendental number'\n" +
            "       title='pi is a transcendental number'\n" +
            "       width='194'\n" +
            "       height='18' />\n" +
            "  </subpod>\n" +
            " </pod>\n" +
            " <pod title='Number line'\n" +
            "     scanner='NumberLine'\n" +
            "     id='NumberLine'\n" +
            "     position='400'\n" +
            "     error='false'\n" +
            "     numsubpods='1'>\n" +
            "  <subpod title=''>\n" +
            "   <img src='http://www3.wolframalpha.com/Calculate/MSP/MSP18291gi96b3cc7b35b1a00005680d1i9c5430164?MSPStoreType=image/gif&amp;s=42'\n" +
            "       alt=''\n" +
            "       title=''\n" +
            "       width='300'\n" +
            "       height='56' />\n" +
            "  </subpod>\n" +
            " </pod>\n" +
            " <pod title='Continued fraction'\n" +
            "     scanner='ContinuedFraction'\n" +
            "     id='ContinuedFraction'\n" +
            "     position='500'\n" +
            "     error='false'\n" +
            "     numsubpods='1'>\n" +
            "  <subpod title=''>\n" +
            "   <img src='http://www3.wolframalpha.com/Calculate/MSP/MSP18301gi96b3cc7b35b1a00005f751c307ff23c34?MSPStoreType=image/gif&amp;s=42'\n" +
            "       alt='[3; 7, 15, 1, 292, 1, 1, 1, 2, 1, 3, 1, 14, 2, 1, 1, 2, 2, 2, 2, 1, 84, 2, 1, 1, 15, ...]'\n" +
            "       title='[3; 7, 15, 1, 292, 1, 1, 1, 2, 1, 3, 1, 14, 2, 1, 1, 2, 2, 2, 2, 1, 84, 2, 1, 1, 15, ...]'\n" +
            "       width='498'\n" +
            "       height='20' />\n" +
            "  </subpod>\n" +
            "  <states count='2'>\n" +
            "   <state name='More terms'\n" +
            "       input='ContinuedFraction__More terms' />\n" +
            "   <state name='Fraction form'\n" +
            "       input='ContinuedFraction__Fraction form' />\n" +
            "  </states>\n" +
            " </pod>\n" +
            " <pod title='Alternative representations'\n" +
            "     scanner='MathematicalFunctionData'\n" +
            "     id='AlternativeRepresentations:MathematicalFunctionIdentityData'\n" +
            "     position='600'\n" +
            "     error='false'\n" +
            "     numsubpods='3'>\n" +
            "  <subpod title=''>\n" +
            "   <img src='http://www3.wolframalpha.com/Calculate/MSP/MSP18311gi96b3cc7b35b1a000033e22bgde9dif9b7?MSPStoreType=image/gif&amp;s=42'\n" +
            "       alt='pi = 180 °'\n" +
            "       title='pi = 180 °'\n" +
            "       width='61'\n" +
            "       height='28' />\n" +
            "  </subpod>\n" +
            "  <subpod title=''>\n" +
            "   <img src='http://www3.wolframalpha.com/Calculate/MSP/MSP18321gi96b3cc7b35b1a00004dgag8eb4ibi8fa4?MSPStoreType=image/gif&amp;s=42'\n" +
            "       alt='pi = -i log(-1)'\n" +
            "       title='pi = -i log(-1)'\n" +
            "       width='97'\n" +
            "       height='28' />\n" +
            "  </subpod>\n" +
            "  <subpod title=''>\n" +
            "   <img src='http://www3.wolframalpha.com/Calculate/MSP/MSP18331gi96b3cc7b35b1a000021cig8f38a2dbcih?MSPStoreType=image/gif&amp;s=42'\n" +
            "       alt='pi = cos^(-1)(-1)'\n" +
            "       title='pi = cos^(-1)(-1)'\n" +
            "       width='94'\n" +
            "       height='28' />\n" +
            "  </subpod>\n" +
            "  <states count='1'>\n" +
            "   <state name='More'\n" +
            "       input='AlternativeRepresentations:MathematicalFunctionIdentityData__More' />\n" +
            "  </states>\n" +
            "  <infos count='4'>\n" +
            "   <info text='log(x) is the natural logarithm'>\n" +
            "    <img src='http://www3.wolframalpha.com/Calculate/MSP/MSP18341gi96b3cc7b35b1a000064eia6cf1ih8916a?MSPStoreType=image/gif&amp;s=42'\n" +
            "        alt='log(x) is the natural logarithm'\n" +
            "        title='log(x) is the natural logarithm'\n" +
            "        width='192'\n" +
            "        height='16' />\n" +
            "    <link url='http://reference.wolfram.com/mathematica/ref/Log.html'\n" +
            "        text='Documentation'\n" +
            "        title='Mathematica' />\n" +
            "    <link url='http://functions.wolfram.com/ElementaryFunctions/Log'\n" +
            "        text='Properties'\n" +
            "        title='Wolfram Functions Site' />\n" +
            "    <link url='http://mathworld.wolfram.com/NaturalLogarithm.html'\n" +
            "        text='Definition'\n" +
            "        title='MathWorld' />\n" +
            "   </info>\n" +
            "   <info text='i is the imaginary unit'>\n" +
            "    <img src='http://www3.wolframalpha.com/Calculate/MSP/MSP18351gi96b3cc7b35b1a000049c86ed711ddc4g8?MSPStoreType=image/gif&amp;s=42'\n" +
            "        alt='i is the imaginary unit'\n" +
            "        title='i is the imaginary unit'\n" +
            "        width='137'\n" +
            "        height='16' />\n" +
            "    <link url='http://reference.wolfram.com/mathematica/ref/I.html'\n" +
            "        text='Documentation'\n" +
            "        title='Documentation' />\n" +
            "    <link url='http://mathworld.wolfram.com/i.html'\n" +
            "        text='Definition'\n" +
            "        title='MathWorld' />\n" +
            "   </info>\n" +
            "   <info text='cos^(-1)(x) is the inverse cosine function'>\n" +
            "    <img src='http://www3.wolframalpha.com/Calculate/MSP/MSP18361gi96b3cc7b35b1a000051e4233b3da5d799?MSPStoreType=image/gif&amp;s=42'\n" +
            "        alt='cos^(-1)(x) is the inverse cosine function'\n" +
            "        title='cos^(-1)(x) is the inverse cosine function'\n" +
            "        width='249'\n" +
            "        height='18' />\n" +
            "    <link url='http://reference.wolfram.com/mathematica/ref/ArcCos.html'\n" +
            "        text='Documentation'\n" +
            "        title='Mathematica' />\n" +
            "    <link url='http://functions.wolfram.com/ElementaryFunctions/ArcCos'\n" +
            "        text='Properties'\n" +
            "        title='Wolfram Functions Site' />\n" +
            "    <link url='http://mathworld.wolfram.com/InverseCosine.html'\n" +
            "        text='Definition'\n" +
            "        title='MathWorld' />\n" +
            "   </info>\n" +
            "   <info>\n" +
            "    <link url='http://functions.wolfram.com/Constants/Pi/27/ShowAll.html'\n" +
            "        text='More information' />\n" +
            "   </info>\n" +
            "  </infos>\n" +
            " </pod>\n" +
            " <pod title='Series representations'\n" +
            "     scanner='MathematicalFunctionData'\n" +
            "     id='SeriesRepresentations:MathematicalFunctionIdentityData'\n" +
            "     position='700'\n" +
            "     error='false'\n" +
            "     numsubpods='3'>\n" +
            "  <subpod title=''>\n" +
            "   <img src='http://www3.wolframalpha.com/Calculate/MSP/MSP18371gi96b3cc7b35b1a000047e98f0e7i28ide1?MSPStoreType=image/gif&amp;s=42'\n" +
            "       alt='pi = 4 sum_(k=0)^infinity (-1)^k/(2 k+1)'\n" +
            "       title='pi = 4 sum_(k=0)^infinity (-1)^k/(2 k+1)'\n" +
            "       width='110'\n" +
            "       height='56' />\n" +
            "  </subpod>\n" +
            "  <subpod title=''>\n" +
            "   <img src='http://www3.wolframalpha.com/Calculate/MSP/MSP18381gi96b3cc7b35b1a000028c65ch03h35c41f?MSPStoreType=image/gif&amp;s=42'\n" +
            "       alt='pi = -2+2 sum_(k=1)^infinity 2^k/(binomial(2 k, k))'\n" +
            "       title='pi = -2+2 sum_(k=1)^infinity 2^k/(binomial(2 k, k))'\n" +
            "       width='139'\n" +
            "       height='69' />\n" +
            "  </subpod>\n" +
            "  <subpod title=''>\n" +
            "   <img src='http://www3.wolframalpha.com/Calculate/MSP/MSP18391gi96b3cc7b35b1a0000178a1ag03d86196f?MSPStoreType=image/gif&amp;s=42'\n" +
            "       alt='pi = sum_(k=0)^infinity (50 k-6)/(2^k binomial(3 k, k))'\n" +
            "       title='pi = sum_(k=0)^infinity (50 k-6)/(2^k binomial(3 k, k))'\n" +
            "       width='111'\n" +
            "       height='67' />\n" +
            "  </subpod>\n" +
            "  <states count='1'>\n" +
            "   <state name='More'\n" +
            "       input='SeriesRepresentations:MathematicalFunctionIdentityData__More' />\n" +
            "  </states>\n" +
            "  <infos count='2'>\n" +
            "   <info text='(n\n" +
            "m) is the binomial coefficient'>\n" +
            "    <img src='http://www3.wolframalpha.com/Calculate/MSP/MSP18401gi96b3cc7b35b1a000039cb145f519b558g?MSPStoreType=image/gif&amp;s=42'\n" +
            "        alt='(n\n" +
            "m) is the binomial coefficient'\n" +
            "        title='(n\n" +
            "m) is the binomial coefficient'\n" +
            "        width='195'\n" +
            "        height='36' />\n" +
            "    <link url='http://reference.wolfram.com/mathematica/ref/Binomial.html'\n" +
            "        text='Documentation'\n" +
            "        title='Mathematica' />\n" +
            "    <link url='http://functions.wolfram.com/GammaBetaErf/Binomial'\n" +
            "        text='Properties'\n" +
            "        title='Wolfram Functions Site' />\n" +
            "    <link url='http://mathworld.wolfram.com/BinomialCoefficient.html'\n" +
            "        text='Definition'\n" +
            "        title='MathWorld' />\n" +
            "   </info>\n" +
            "   <info>\n" +
            "    <link url='http://functions.wolfram.com/Constants/Pi/06/ShowAll.html'\n" +
            "        text='More information' />\n" +
            "   </info>\n" +
            "  </infos>\n" +
            " </pod>\n" +
            " <pod title='Integral representations'\n" +
            "     scanner='MathematicalFunctionData'\n" +
            "     id='IntegralRepresentations:MathematicalFunctionIdentityData'\n" +
            "     position='800'\n" +
            "     error='false'\n" +
            "     numsubpods='3'>\n" +
            "  <subpod title=''>\n" +
            "   <img src='http://www3.wolframalpha.com/Calculate/MSP/MSP18411gi96b3cc7b35b1a00004i8ah02deic7e5dh?MSPStoreType=image/gif&amp;s=42'\n" +
            "       alt='pi = 2 integral_0^infinity 1/(t^2+1) dt'\n" +
            "       title='pi = 2 integral_0^infinity 1/(t^2+1) dt'\n" +
            "       width='126'\n" +
            "       height='44' />\n" +
            "  </subpod>\n" +
            "  <subpod title=''>\n" +
            "   <img src='http://www3.wolframalpha.com/Calculate/MSP/MSP18421gi96b3cc7b35b1a00001ce2d3gbg7he790e?MSPStoreType=image/gif&amp;s=42'\n" +
            "       alt='pi = 4 integral_0^1 sqrt(1-t^2) dt'\n" +
            "       title='pi = 4 integral_0^1 sqrt(1-t^2) dt'\n" +
            "       width='138'\n" +
            "       height='45' />\n" +
            "  </subpod>\n" +
            "  <subpod title=''>\n" +
            "   <img src='http://www3.wolframalpha.com/Calculate/MSP/MSP18431gi96b3cc7b35b1a00000caid08c4240f9hi?MSPStoreType=image/gif&amp;s=42'\n" +
            "       alt='pi = 2 integral_0^infinity (sin(t))/t dt'\n" +
            "       title='pi = 2 integral_0^infinity (sin(t))/t dt'\n" +
            "       width='124'\n" +
            "       height='45' />\n" +
            "  </subpod>\n" +
            "  <states count='1'>\n" +
            "   <state name='More'\n" +
            "       input='IntegralRepresentations:MathematicalFunctionIdentityData__More' />\n" +
            "  </states>\n" +
            "  <infos count='1'>\n" +
            "   <info>\n" +
            "    <link url='http://functions.wolfram.com/Constants/Pi/07/ShowAll.html'\n" +
            "        text='More information' />\n" +
            "   </info>\n" +
            "  </infos>\n" +
            " </pod>\n" +
            " <assumptions count='1'>\n" +
            "  <assumption type='Clash'\n" +
            "      word='pi'\n" +
            "      template='Assuming &quot;${word}&quot; is ${desc1}. Use as ${desc2} instead'\n" +
            "      count='6'>\n" +
            "   <value name='NamedConstant'\n" +
            "       desc='a mathematical constant'\n" +
            "       input='*C.pi-_*NamedConstant-' />\n" +
            "   <value name='Character'\n" +
            "       desc='a character'\n" +
            "       input='*C.pi-_*Character-' />\n" +
            "   <value name='MathWorld'\n" +
            "       desc=' referring to a mathematical definition'\n" +
            "       input='*C.pi-_*MathWorld-' />\n" +
            "   <value name='MathWorldClass'\n" +
            "       desc='a class of mathematical terms'\n" +
            "       input='*C.pi-_*MathWorldClass-' />\n" +
            "   <value name='Movie'\n" +
            "       desc='a movie'\n" +
            "       input='*C.pi-_*Movie-' />\n" +
            "   <value name='Word'\n" +
            "       desc='a word'\n" +
            "       input='*C.pi-_*Word-' />\n" +
            "  </assumption>\n" +
            " </assumptions>\n" +
            "</queryresult>";



}
