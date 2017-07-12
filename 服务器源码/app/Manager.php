<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Manager extends Model
{

    protected $table='manager';
    protected $primaryKey = "id";
    /**
     * The attributes that are mass assignable.
     *
     * @var array
     */
    protected $fillable = [
        'name', 'email', 'password',
    ];

    /**
     * The attributes that should be hidden for arrays.
     *
     * @var array
     */
    protected $hidden = [
        'password', 'remember_token','token_expire',
    ];
    public $timestamps =true;
}
