<?php

namespace App\Repository;

use App\Entity\Categorie2eq;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\ORM\OptimisticLockException;
use Doctrine\ORM\ORMException;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Categorie2eq|null find($id, $lockMode = null, $lockVersion = null)
 * @method Categorie2eq|null findOneBy(array $criteria, array $orderBy = null)
 * @method Categorie2eq[]    findAll()
 * @method Categorie2eq[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class Categorie2eqRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Categorie2eq::class);
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function add(Categorie2eq $entity, bool $flush = true): void
    {
        $this->_em->persist($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function remove(Categorie2eq $entity, bool $flush = true): void
    {
        $this->_em->remove($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }

    // /**
    //  * @return Categorie2eq[] Returns an array of Categorie2eq objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('c.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Categorie2eq
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */

    public function findCategorie_1($nom){
        $entityManager=$this->getEntityManager();
        $query=$entityManager->createQuery("SELECT s FROM App\Entity\Categorie2eq s WHERE s.name = '$nom'  ");
        return $query->getResult();
    }
}
