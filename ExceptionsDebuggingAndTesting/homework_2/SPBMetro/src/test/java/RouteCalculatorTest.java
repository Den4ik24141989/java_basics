import core.Line;
import core.Station;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import junit.framework.TestCase;

public class RouteCalculatorTest extends TestCase {
    StationIndex stationIndex;
    RouteCalculator calculator;

    Station veselaya;
    Station sadovaya;
    Station gorkogo;
    Station kooperativnaya;

    List<Station> noTransferRoute;
    List<Station> oneTransferRoute;
    List<Station> twoTransfersRoute;

    @Override
    public void setUp() throws Exception {
        Line line1 = new Line(1, "Первая");
        Line line2 = new Line(2, "Вторая");
        Line line3 = new Line(3, "Третья");

        stationIndex = new StationIndex();
        Stream.of(line1, line2, line3).forEach(stationIndex::addLine);

        veselaya = new Station("Веселая", line1);
        gorkogo = new Station("Горького", line1);
        kooperativnaya = new Station("Кооперативная", line2);
        sadovaya = new Station("Садовая", line3);
        Station aerodromnaya = new Station("Аэродромная", line1);
        Station sportivnaya = new Station("Спортивная", line2);
        Station lenina = new Station("Ленина", line2);
        Station krasnaya = new Station("Красная", line3);
        Station centralnaya = new Station("Центральная", line3);

        Stream.of(veselaya, aerodromnaya, gorkogo, kooperativnaya, sportivnaya, lenina, sadovaya, krasnaya,
                centralnaya).peek(s -> s.getLine().addStation(s)).forEach(stationIndex::addStation);
        stationIndex.addConnection(Stream.of(aerodromnaya, sportivnaya).collect(Collectors.toList()));
        stationIndex.addConnection(Stream.of(lenina, centralnaya).collect(Collectors.toList()));
        stationIndex.getConnectedStations(aerodromnaya);
        stationIndex.getConnectedStations(lenina);

        calculator = new RouteCalculator(stationIndex);

        noTransferRoute = Stream.of(veselaya, aerodromnaya, gorkogo).collect(Collectors.toList());
        oneTransferRoute = Stream.of(veselaya, aerodromnaya, sportivnaya, kooperativnaya)
                .collect(Collectors.toList());
        twoTransfersRoute = Stream
                .of(veselaya, aerodromnaya, sportivnaya, lenina, centralnaya, krasnaya, sadovaya)
                .collect(Collectors.toList());
    }

    public void testCalculateDuration() {
        double actual = RouteCalculator.calculateDuration(oneTransferRoute);
        double expected = 8.5;
        assertEquals(expected, actual);
    }

    public void testGetShortestRoute() {
        List<Station> actualNoTransfer = calculator.getShortestRoute(veselaya, gorkogo);
        List<Station> actualTwoTransfer = calculator.getShortestRoute(veselaya, kooperativnaya);
        List<Station> actualThreeTransfers = calculator.getShortestRoute(veselaya, sadovaya);

        List<Station> expectedNoTransfers = noTransferRoute;
        List<Station> expectedTwoTransfers = oneTransferRoute;
        List<Station> expectedThreeTransfers = twoTransfersRoute;

        assertEquals(actualNoTransfer, expectedNoTransfers);
        assertEquals(actualTwoTransfer, expectedTwoTransfers);
        assertEquals(actualThreeTransfers, expectedThreeTransfers);
    }
}