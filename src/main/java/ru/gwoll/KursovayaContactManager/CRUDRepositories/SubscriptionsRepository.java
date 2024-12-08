package ru.gwoll.KursovayaContactManager.CRUDRepositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gwoll.KursovayaContactManager.Entities.Subscription;
import ru.gwoll.KursovayaContactManager.Entities.User;

import java.util.List;

/**
 * Репозиторий для работы с сущностью {@link Subscription}.
 *
 * Этот интерфейс предоставляет CRUD-операции для работы с подписками пользователей
 * в базе данных, а также дополнительные запросы для получения подписок и подписанных пользователей.
 * Репозиторий расширяет интерфейс {@link CrudRepository}, который предоставляет базовые операции
 * для работы с базой данных.
 */
@Repository
public interface SubscriptionsRepository extends CrudRepository<Subscription, Long> {
    /**
     * Находит все подписки для конкретного пользователя.
     *
     * @param user {@link User} - пользователь, для которого ищутся подписки.
     * @return Список подписок {@link List<Subscription>} для указанного пользователя.
     */
    @Query("SELECT s FROM Subscription s WHERE s.user = :user")
    List<Subscription> findSubscriptionsByUser(User user);

    /**
     * Находит все подписки, где пользователь является подписанным.
     *
     * @param subscribedUser {@link User} - подписанный пользователь.
     * @return Список подписок {@link List<Subscription>} для указанного подписанного пользователя.
     */
    @Query("SELECT s FROM Subscription s WHERE s.subscribedUser = :subscribedUser")
    List<Subscription> findSubscriptionsBySubscribedUser(User subscribedUser);

    /**
     * Находит всех пользователей, на которых есть подписки.
     *
     * @return Список всех пользователей {@link List<User>}, на которых подписаны другие пользователи.
     */
    @Query("SELECT s.subscribedUser FROM Subscription s")
    List<User> findAllSubscribedUsers();

    /**
     * Находит подписку для пары пользователей: пользователя и подписанного пользователя.
     *
     * @param user {@link User} - пользователь, который подписан на другого пользователя.
     * @param subscribedUser {@link User} - подписанный пользователь.
     * @return {@link Subscription} - подписка между двумя пользователями.
     */
    Subscription findSubscriptionByUserAndSubscribedUser(User user, User subscribedUser);
}
