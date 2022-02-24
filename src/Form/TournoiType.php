<?php

namespace App\Form;

use App\Entity\Tournoi;
use App\Entity\TypeTournoi;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class TournoiType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nom_tour')
            ->add('desc_tour')
            ->add('nbr_joueur')
            ->add('tour')
            ->add('tour',EntityType::class, [
                'class' => TypeTournoi::class,
                'choice_label' => 'nom_type',
            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Tournoi::class,
        ]);
    }
}
