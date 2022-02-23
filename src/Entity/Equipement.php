<?php

namespace App\Entity;

use App\Repository\EquipementRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=EquipementRepository::class)
 */
class Equipement
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank
     */
    private $name;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank
     */
    private $categorie;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank
     */
    private $marque;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Ajouter une image jpg")
     * @Assert\File(mimeTypes={ "image/jpeg" })
     */
    private $image;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank
     */
    private $description;

    /**
     * @ORM\OneToMany(targetEntity=ImageEq::class, mappedBy="equipement", orphanRemoval=true)
     * @Assert\NotBlank
     */
    private $imageEqs;

    public function __construct()
    {
        $this->imageEqs = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getName(): ?string
    {
        return $this->name;
    }

    public function setName(string $name): self
    {
        $this->name = $name;

        return $this;
    }

    public function getCategorie(): ?string
    {
        return $this->categorie;
    }

    public function setCategorie(string $categorie): self
    {
        $this->categorie = $categorie;

        return $this;
    }

    public function getMarque(): ?string
    {
        return $this->marque;
    }

    public function setMarque(string $marque): self
    {
        $this->marque = $marque;

        return $this;
    }

    public function getImage(): ?string
    {
        return $this->image;
    }

    public function setImage(string $image): self
    {
        $this->image = $image;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }

    /**
     * @return Collection|ImageEq[]
     */
    public function getImageEqs(): Collection
    {
        return $this->imageEqs;
    }

    public function addImageEq(ImageEq $imageEq): self
    {
        if (!$this->imageEqs->contains($imageEq)) {
            $this->imageEqs[] = $imageEq;
            $imageEq->setEquipement($this);
        }

        return $this;
    }

    public function removeImageEq(ImageEq $imageEq): self
    {
        if ($this->imageEqs->removeElement($imageEq)) {
            // set the owning side to null (unless already changed)
            if ($imageEq->getEquipement() === $this) {
                $imageEq->setEquipement(null);
            }
        }

        return $this;
    }
}
