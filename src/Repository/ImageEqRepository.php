<?php

namespace App\Repository;

use App\Entity\ImageEq;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method ImageEq|null find($id, $lockMode = null, $lockVersion = null)
 * @method ImageEq|null findOneBy(array $criteria, array $orderBy = null)
 * @method ImageEq[]    findAll()
 * @method ImageEq[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ImageEqRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, ImageEq::class);
    }

    // /**
    //  * @return ImageEq[] Returns an array of ImageEq objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('i')
            ->andWhere('i.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('i.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?ImageEq
    {
        return $this->createQueryBuilder('i')
            ->andWhere('i.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
