package com.cinema;

import com.cinema.exception.AuthenticationException;
import com.cinema.lib.Injector;
import com.cinema.model.CinemaHall;
import com.cinema.model.Movie;
import com.cinema.model.MovieSession;
import com.cinema.model.ShoppingCart;
import com.cinema.model.User;
import com.cinema.service.AuthenticationService;
import com.cinema.service.CinemaHallService;
import com.cinema.service.MovieService;
import com.cinema.service.MovieSessionService;
import com.cinema.service.ShoppingCartService;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    private static Injector injector = Injector.getInstance("com.cinema");

    public static void main(String[] args) throws AuthenticationException {
        Movie movie1 = new Movie();
        Movie movie2 = new Movie();
        Movie movie3 = new Movie();
        movie1.setTitle("Fast and Furious");
        movie2.setTitle("The Dark Knight");
        movie3.setTitle("Bucket List");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie1);
        movieService.add(movie2);
        movieService.add(movie3);
        movieService.getAll().forEach(System.out::println);

        CinemaHall cinemaHall1 = new CinemaHall();
        cinemaHall1.setCapacity(100);
        cinemaHall1.setDescription("Blue");
        CinemaHall cinemaHall2 = new CinemaHall();
        cinemaHall2.setCapacity(200);
        cinemaHall2.setDescription("Red");
        CinemaHall cinemaHall3 = new CinemaHall();
        cinemaHall3.setCapacity(300);
        cinemaHall3.setDescription("Green");
        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);
        cinemaHallService.add(cinemaHall1);
        cinemaHallService.add(cinemaHall2);
        cinemaHallService.add(cinemaHall3);
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSession movieSession1 = new MovieSession();
        movieSession1.setMovie(movie1);
        movieSession1.setCinemaHall(cinemaHall1);
        LocalDateTime localDate1 = LocalDateTime.of(2020, 1, 10, 14, 30, 0);
        movieSession1.setShowTime(localDate1);
        MovieSession movieSession2 = new MovieSession();
        movieSession2.setMovie(movie1);
        movieSession2.setCinemaHall(cinemaHall2);
        LocalDateTime localDate2 = LocalDateTime.of(2020, 1, 11, 14, 30, 0);
        movieSession2.setShowTime(localDate2);
        MovieSession movieSession3 = new MovieSession();
        movieSession3.setMovie(movie1);
        movieSession3.setCinemaHall(cinemaHall3);
        LocalDateTime localDate3 = LocalDateTime.of(2020, 1, 10, 18, 30, 0);
        movieSession3.setShowTime(localDate3);
        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession1);
        movieSessionService.add(movieSession2);
        movieSessionService.add(movieSession3);
        movieSessionService.findAvailableSessions(movie1.getId(),
                LocalDate.of(2020, 1, 10)).forEach(System.out::println);

        AuthenticationService authenticationService =
                (AuthenticationService) injector.getInstance(AuthenticationService.class);
        User max = authenticationService.register("max", "1234");
        User bob = authenticationService.register("bob", "1234");
        System.out.println(authenticationService.login("max", "1234"));

        ShoppingCartService shoppingCartService =
                (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
        shoppingCartService.addSession(movieSession1,max);
        shoppingCartService.addSession(movieSession2,max);
        ShoppingCart byUser = shoppingCartService.getByUser(max);
        System.out.println(byUser);
        shoppingCartService.clear(byUser);
        System.out.println(byUser);
    }
}
