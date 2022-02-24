<?php

namespace App\Entity;
use Symfony\Component\Validator\Constraints as Assert;
use App\Repository\TypeTournoiRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;

use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=TypeTournoiRepository::class)
 */
class TypeTournoi
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="please enter nom")
     */
    private $nom_type;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="please enter description")
     */
    private $desc_type;

    /**
     * @ORM\OneToMany(targetEntity=Tournoi::class, mappedBy="tour")
     */
    private $tournois;

    public function __construct()
    {
        $this->tournois = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNomType(): ?string
    {
        return $this->nom_type;
    }

    public function setNomType(string $nom_type): self
    {
        $this->nom_type = $nom_type;

        return $this;
    }

    public function getDescType(): ?string
    {
        return $this->desc_type;
    }

    public function setDescType(string $desc_type): self
    {
        $this->desc_type = $desc_type;

        return $this;
    }

    /**
     * @return Collection<int, Tournoi>
     */
    public function getTournois(): Collection
    {
        return $this->tournois;
    }

    public function addTournoi(Tournoi $tournoi): self
    {
        if (!$this->tournois->contains($tournoi)) {
            $this->tournois[] = $tournoi;
            $tournoi->setTour($this);
        }

        return $this;
    }

    public function removeTournoi(Tournoi $tournoi): self
    {
        if ($this->tournois->removeElement($tournoi)) {
            // set the owning side to null (unless already changed)
            if ($tournoi->getTour() === $this) {
                $tournoi->setTour(null);
            }
        }

        return $this;
    }

    public function __toString() {
        return (string) $this->id;
    }
}
