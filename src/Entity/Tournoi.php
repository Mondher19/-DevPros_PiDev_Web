<?php

namespace App\Entity;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use App\Repository\TournoiRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=TournoiRepository::class)
 */
class Tournoi
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id_tour;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="please enter nom")
     */
    private $nom_tour;

    /**
     * @ORM\Column(type="string", length=255)
     *Assert\NotBlank(message="please enter description")
     */
    private $desc_tour;

    /**
     * @ORM\Column(type="integer")
     */
    private $nbr_joueur;

    /**
     * @ORM\ManyToOne(targetEntity=TypeTournoi::class, inversedBy="tournois" ,cascade={"remove"})
     * @ORM\JoinColumn(name="tour", referencedColumnName="id" , onDelete="CASCADE" )
     */
    private $tour;

    /**
     * @return mixed
     */
    public function getIdTour()
    {
        return $this->id_tour;
    }



    public function getNomTour(): ?string
    {
        return $this->nom_tour;
    }

    public function setNomTour(string $nom_tour ): self
    {
        $this->nom_tour = $nom_tour;

        return $this;
    }

    public function getDescTour(): ?string
    {
        return $this->desc_tour;
    }

    public function setDescTour(string $desc_tour): self
    {
        $this->desc_tour = $desc_tour;

        return $this;
    }

    public function getNbrJoueur(): ?int
    {
        return $this->nbr_joueur;
    }

    public function setNbrJoueur(int $nbr_joueur): self
    {
        $this->nbr_joueur = $nbr_joueur;

        return $this;
    }

    public function getTour(): ?TypeTournoi
    {
        return $this->tour;
    }

    public function setTour(?TypeTournoi $tour): self
    {
        $this->tour = $tour;

        return $this;
    }
}
